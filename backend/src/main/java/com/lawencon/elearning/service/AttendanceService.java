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
import com.lawencon.elearning.dao.ScheduleDao;
import com.lawencon.elearning.dto.attendance.AttendanceDataDto;
import com.lawencon.elearning.dto.attendance.AttendanceInsertReqDto;
import com.lawencon.elearning.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.model.Attendance;
import com.lawencon.elearning.model.ClassDtl;
import com.lawencon.elearning.model.Schedule;
import com.lawencon.elearning.security.PrincipalService;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private ClassDtlDao classDtlDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final AttendanceInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            if(attendanceDao.valNotAttend(data.getClassDtlId(), data.getScheduleId()).size() >= 1) {
                throw new RuntimeException("You had attend this schedule!");
            }
            final Attendance attendance = new Attendance();
            attendance.setApproval(false);

            final Optional<ClassDtl> optionalFk1 = classDtlDao.getById(data.getClassDtlId());
            ClassDtl dtl = optionalFk1.get();
            attendance.setClassDtl(dtl);
            final Optional<Schedule> optionalFk2 = scheduleDao.getById(data.getScheduleId());
            Schedule schedule = optionalFk2.get();
            attendance.setSchedule(schedule);

            //you can verify if schedule and classdtl matches hdr match, and if class detail is his class
            
            attendance.setCreatedBy(principalService.getPrincipal().getId());
            final Attendance insertOne = attendanceDao.insert(attendance);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.ATTENDANCE.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final AttendanceUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Attendance> optional = attendanceDao.getById(data.getId());
        Attendance updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setApproval(data.getApproval());
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = attendanceDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.ATTENDANCE.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public DataResDto<AttendanceDataDto> getById(final Long id) {
        final Optional<Attendance> optional = attendanceDao.getById(id);
        Attendance findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final AttendanceDataDto responseDb = new AttendanceDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setApproval(findOne.getApproval());
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
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<AttendanceDataDto> responseBe = new DataResDto<AttendanceDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.ATTENDANCE.getResponse() + " not found!");
        }
    }

    public DataListResDto<AttendanceDataDto> getAllByInstructor(final Long scheduleId, final Long classHdrId) {
        final List<AttendanceDataDto> responseDb = new ArrayList<>();
        final List<Attendance> find = attendanceDao.getAllByInstructor(scheduleId, classHdrId);
        for (int i = 0; i < find.size(); i++) {
            final Attendance attendance = find.get(i);
            final AttendanceDataDto result = new AttendanceDataDto();
            result.setId(attendance.getId());
            result.setApproval(attendance.getApproval());
            result.setClassDtlId(attendance.getClassDtl().getId());
            result.setStdId(attendance.getClassDtl().getStudent().getId());
            result.setStdEmail(attendance.getClassDtl().getStudent().getEmail());
            result.setStdFullname(attendance.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(attendance.getClassDtl().getClassHdr().getId());
            result.setClassHdrCode(attendance.getClassDtl().getClassHdr().getClassHdrCode());
            result.setClassHdrSubject(attendance.getClassDtl().getClassHdr().getClassHdrSubject());
            result.setInsId(attendance.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(attendance.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(attendance.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setShceduleId(attendance.getSchedule().getId());
            result.setStartTime(attendance.getSchedule().getStartTime());
            result.setEndTime(attendance.getSchedule().getEndTime());
            result.setMaterialId(attendance.getSchedule().getMaterial().getId());
            result.setMaterialCode(attendance.getSchedule().getMaterial().getMaterialCode());
            result.setMaterialSubject(attendance.getSchedule().getMaterial().getMaterialSubject());
            result.setActivityId(attendance.getSchedule().getMaterial().getActivity().getId());
            result.setActivityCode(attendance.getSchedule().getMaterial().getActivity().getCode());
            result.setActivityType(attendance.getSchedule().getMaterial().getActivity().getType());
            result.setVer(attendance.getVer());
            result.setIsActive(attendance.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<AttendanceDataDto> responseBe = new DataListResDto<AttendanceDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

    public DataListResDto<AttendanceDataDto> getAllByStudent(final Long scheduleId, final Long classDtlId) {
        final List<AttendanceDataDto> responseDb = new ArrayList<>();
        final List<Attendance> find = attendanceDao.getAllByStudent(scheduleId, classDtlId);
        for (int i = 0; i < find.size(); i++) {
            final Attendance attendance = find.get(i);
            final AttendanceDataDto result = new AttendanceDataDto();
            result.setId(attendance.getId());
            result.setApproval(attendance.getApproval());
            result.setClassDtlId(attendance.getClassDtl().getId());
            result.setStdId(attendance.getClassDtl().getStudent().getId());
            result.setStdEmail(attendance.getClassDtl().getStudent().getEmail());
            result.setStdFullname(attendance.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(attendance.getClassDtl().getClassHdr().getId());
            result.setClassHdrCode(attendance.getClassDtl().getClassHdr().getClassHdrCode());
            result.setClassHdrSubject(attendance.getClassDtl().getClassHdr().getClassHdrSubject());
            result.setInsId(attendance.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(attendance.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(attendance.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setShceduleId(attendance.getSchedule().getId());
            result.setStartTime(attendance.getSchedule().getStartTime());
            result.setEndTime(attendance.getSchedule().getEndTime());
            result.setMaterialId(attendance.getSchedule().getMaterial().getId());
            result.setMaterialCode(attendance.getSchedule().getMaterial().getMaterialCode());
            result.setMaterialSubject(attendance.getSchedule().getMaterial().getMaterialSubject());
            result.setActivityId(attendance.getSchedule().getMaterial().getActivity().getId());
            result.setActivityCode(attendance.getSchedule().getMaterial().getActivity().getCode());
            result.setActivityType(attendance.getSchedule().getMaterial().getActivity().getType());
            result.setVer(attendance.getVer());
            result.setIsActive(attendance.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<AttendanceDataDto> responseBe = new DataListResDto<AttendanceDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
