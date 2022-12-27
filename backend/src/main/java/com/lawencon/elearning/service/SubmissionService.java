package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.dao.AttendanceDao;
import com.lawencon.elearning.dao.ClassDtlDao;
import com.lawencon.elearning.dao.FileDao;
import com.lawencon.elearning.dao.ScheduleDao;
import com.lawencon.elearning.dao.SubmissionDao;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.submission.SubmissionDataDto;
import com.lawencon.elearning.dto.submission.SubmissionInsertReqDto;
import com.lawencon.elearning.dto.submission.SubmissionListDataDto;
import com.lawencon.elearning.dto.submission.SubmissionUpdateReqDto;
import com.lawencon.elearning.model.ClassDtl;
import com.lawencon.elearning.model.File;
import com.lawencon.elearning.model.Schedule;
import com.lawencon.elearning.model.Submission;
import com.lawencon.elearning.security.PrincipalService;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionDao submissionDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private ClassDtlDao classDtlDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private FileDao fileDao;
    
    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final SubmissionInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            if(attendanceDao.valAttendance(data.getScheduleId(), data.getClassDtlId()).size() != 1) {
                throw new RuntimeException("You have to fill in the attendance first!");
            }
            if(submissionDao.valNotSubmit(data.getClassDtlId(), data.getScheduleId()).size() >= 1) {
                throw new RuntimeException("You have already submitted the file!");
            }
            final Submission submission = new Submission();
            final Optional<ClassDtl> optionalFk1 = classDtlDao.getById(data.getClassDtlId());
            ClassDtl dtl = optionalFk1.get();
            submission.setClassDtl(dtl);
            final Optional<Schedule> optionalFk2 = scheduleDao.getById(data.getScheduleId());
            Schedule schedule = optionalFk2.get();
            submission.setSchedule(schedule);

            File file = new File();
            file.setFileCode(data.getFileCode());
            file.setFileExt(data.getFileExt());
            file.setCreatedBy(principalService.getPrincipal().getId());
            file = fileDao.insert(file);
            submission.setFile(file);

            submission.setCreatedBy(principalService.getPrincipal().getId());
            final Submission insertOne = submissionDao.insert(submission);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.SUBMISSION.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final SubmissionUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Submission> optional = submissionDao.getById(data.getId());
        Submission updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setScore(data.getScore());
                if(data.getFileCode()!=null && data.getFileExt()!=null){
                    File file = new File();
                    file.setFileCode(data.getFileCode());
                    file.setFileExt(data.getFileExt());
                    file.setCreatedBy(principalService.getPrincipal().getId());
                    file = fileDao.insert(file);
                    updateOne.setFile(file);
                }
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = submissionDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.SUBMISSION.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public DataResDto<SubmissionDataDto> getById(final Long id) {
        final Optional<Submission> optional = submissionDao.getById(id);
        Submission findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final SubmissionDataDto responseDb = new SubmissionDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setScore(findOne.getScore());
            responseDb.setClassDtlId(findOne.getClassDtl().getId());
            responseDb.setStdId(findOne.getClassDtl().getStudent().getId());
            responseDb.setStdEmail(findOne.getClassDtl().getStudent().getEmail());
            responseDb.setStdFullname(findOne.getClassDtl().getStudent().getFullname());
            responseDb.setClassHdrId(findOne.getClassDtl().getClassHdr().getId());
            responseDb.setClassHdrCode(findOne.getClassDtl().getClassHdr().getClassHdrCode());
            responseDb.setClassHdrSubject(findOne.getClassDtl().getClassHdr().getClassHdrSubject());
            responseDb.setInsId(findOne.getClassDtl().getClassHdr().getInstructor().getId());
            responseDb.setInsEmail(findOne.getClassDtl().getClassHdr().getInstructor().getEmail());
            responseDb.setInsFullname(findOne.getClassDtl().getClassHdr().getInstructor().getFullname());
            responseDb.setShceduleId(findOne.getSchedule().getId());
            responseDb.setStartTime(findOne.getSchedule().getStartTime());
            responseDb.setEndTime(findOne.getSchedule().getEndTime());
            responseDb.setMaterialId(findOne.getSchedule().getMaterial().getId());
            responseDb.setMaterialCode(findOne.getSchedule().getMaterial().getMaterialCode());
            responseDb.setMaterialSubject(findOne.getSchedule().getMaterial().getMaterialSubject());
            responseDb.setActivityId(findOne.getSchedule().getMaterial().getActivity().getId());
            responseDb.setActivityCode(findOne.getSchedule().getMaterial().getActivity().getCode());
            responseDb.setActivityType(findOne.getSchedule().getMaterial().getActivity().getType());
            responseDb.setFileId(findOne.getFile().getId());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<SubmissionDataDto> responseBe = new DataResDto<SubmissionDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.SUBMISSION.getResponse() + " not found!");
        }
    }

    public SubmissionListDataDto getAllByInstructor(final Long scheduleId, final Long classHdrId) {
        final List<SubmissionDataDto> responseDb = new ArrayList<>();
        final List<Submission> find = submissionDao.getAllByInstructor(scheduleId, classHdrId);
        for (int i = 0; i < find.size(); i++) {
            final Submission submission = find.get(i);
            final SubmissionDataDto result = new SubmissionDataDto();
            result.setId(submission.getId());
            result.setScore(submission.getScore());
            result.setClassDtlId(submission.getClassDtl().getId());
            result.setStdId(submission.getClassDtl().getStudent().getId());
            result.setStdEmail(submission.getClassDtl().getStudent().getEmail());
            result.setStdFullname(submission.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(submission.getClassDtl().getClassHdr().getId());
            result.setClassHdrCode(submission.getClassDtl().getClassHdr().getClassHdrCode());
            result.setClassHdrSubject(submission.getClassDtl().getClassHdr().getClassHdrSubject());
            result.setInsId(submission.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(submission.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(submission.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setShceduleId(submission.getSchedule().getId());
            result.setStartTime(submission.getSchedule().getStartTime());
            result.setEndTime(submission.getSchedule().getEndTime());
            result.setMaterialId(submission.getSchedule().getMaterial().getId());
            result.setMaterialCode(submission.getSchedule().getMaterial().getMaterialCode());
            result.setMaterialSubject(submission.getSchedule().getMaterial().getMaterialSubject());
            result.setActivityId(submission.getSchedule().getMaterial().getActivity().getId());
            result.setActivityCode(submission.getSchedule().getMaterial().getActivity().getCode());
            result.setActivityType(submission.getSchedule().getMaterial().getActivity().getType());
            result.setFileId(submission.getFile().getId());
            result.setVer(submission.getVer());
            result.setIsActive(submission.getIsActive());
            responseDb.add(result);
        }
        final SubmissionListDataDto responseBe = new SubmissionListDataDto();
        responseBe.setData(responseDb);
        return responseBe;
    }

    public SubmissionListDataDto getAllByStudent(final Long scheduleId, final Long classDtlId) {
        final List<SubmissionDataDto> responseDb = new ArrayList<>();
        final List<Submission> find = submissionDao.getAllByStudent(scheduleId, classDtlId);
        for (int i = 0; i < find.size(); i++) {
            final Submission submissions = find.get(i);
            final SubmissionDataDto result = new SubmissionDataDto();
            result.setId(submissions.getId());
            result.setScore(submissions.getScore());
            result.setClassDtlId(submissions.getClassDtl().getId());
            result.setStdId(submissions.getClassDtl().getStudent().getId());
            result.setStdEmail(submissions.getClassDtl().getStudent().getEmail());
            result.setStdFullname(submissions.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(submissions.getClassDtl().getClassHdr().getId());
            result.setClassHdrCode(submissions.getClassDtl().getClassHdr().getClassHdrCode());
            result.setClassHdrSubject(submissions.getClassDtl().getClassHdr().getClassHdrSubject());
            result.setInsId(submissions.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(submissions.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(submissions.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setShceduleId(submissions.getSchedule().getId());
            result.setStartTime(submissions.getSchedule().getStartTime());
            result.setEndTime(submissions.getSchedule().getEndTime());
            result.setMaterialId(submissions.getSchedule().getMaterial().getId());
            result.setMaterialCode(submissions.getSchedule().getMaterial().getMaterialCode());
            result.setMaterialSubject(submissions.getSchedule().getMaterial().getMaterialSubject());
            result.setActivityId(submissions.getSchedule().getMaterial().getActivity().getId());
            result.setActivityCode(submissions.getSchedule().getMaterial().getActivity().getCode());
            result.setActivityType(submissions.getSchedule().getMaterial().getActivity().getType());
            result.setFileId(submissions.getFile().getId());
            result.setVer(submissions.getVer());
            result.setIsActive(submissions.getIsActive());
            responseDb.add(result);
        }
        final SubmissionListDataDto responseBe = new SubmissionListDataDto();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
