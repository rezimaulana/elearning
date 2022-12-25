package com.lawencon.elearning.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.dao.ActivityDao;
import com.lawencon.elearning.dao.AttachmentDao;
import com.lawencon.elearning.dao.ClassHdrDao;
import com.lawencon.elearning.dao.FileDao;
import com.lawencon.elearning.dao.MaterialDao;
import com.lawencon.elearning.dao.ScheduleDao;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.schedule.ScheduleDataDto;
import com.lawencon.elearning.dto.schedule.ScheduleInsertReqDto;
import com.lawencon.elearning.dto.schedule.ScheduleListDataDto;
import com.lawencon.elearning.dto.schedule.ScheduleUpdateReqDto;
import com.lawencon.elearning.model.Activity;
import com.lawencon.elearning.model.Attachment;
import com.lawencon.elearning.model.ClassHdr;
import com.lawencon.elearning.model.File;
import com.lawencon.elearning.model.Material;
import com.lawencon.elearning.model.Schedule;
import com.lawencon.elearning.security.PrincipalService;
import com.lawencon.elearning.util.GenerateCodeUtil;

@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private GenerateCodeUtil generateCodeUtil;

    @Autowired
    private ClassHdrDao classHdrDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private FileDao fileDao;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final ScheduleInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final Schedule schedule = new Schedule();
            schedule.setStartTime(Timestamp.valueOf(data.getStartTime()).toLocalDateTime());
            schedule.setEndTime(Timestamp.valueOf(data.getEndTime()).toLocalDateTime());
            
            Material material = new Material();
            material.setMaterialCode("MTRAL"+generateCodeUtil.generateDigit(5));
            material.setMaterialSubject(data.getMaterialSubject());
            material.setMaterialDescription(data.getMaterialDescription());
            final Optional<ClassHdr> optionalFk1 = classHdrDao.getById(data.getClassHdrId());
            material.setClassHdr(optionalFk1.get());
            final Optional<Activity> optionalFk2 = activityDao.getById(data.getActivityId());
            material.setActivity(optionalFk2.get());
            material.setCreatedBy(principalService.getPrincipal().getId());
            material = materialDao.insert(material);
            schedule.setMaterial(material);  
            schedule.setCreatedBy(principalService.getPrincipal().getId());
            final Schedule insertOne = scheduleDao.insert(schedule);

            for(int i=0; i<data.getFile().size(); i++){
                File file = new File();
                file.setFileCode(data.getFile().get(i).getFileCode());
                file.setFileExt(data.getFile().get(i).getFileExt());
                file.setCreatedBy(principalService.getPrincipal().getId());
                file = fileDao.insert(file);
                Attachment attachment = new Attachment();
                attachment.setMaterial(material);
                attachment.setFile(file);
                attachment.setCreatedBy(principalService.getPrincipal().getId());
                attachment = attachmentDao.insert(attachment);
            }

            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.SCHEDULE.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final ScheduleUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Schedule> optional = scheduleDao.getById(data.getId());
        Schedule updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setStartTime(Timestamp.valueOf(data.getStartTime()).toLocalDateTime());
                updateOne.setEndTime(Timestamp.valueOf(data.getEndTime()).toLocalDateTime());
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = scheduleDao.update(updateOne);

                final Optional<Material> optionalFk1 = materialDao.getById(updateOne.getMaterial().getId());
                Material material = optionalFk1.get();
                material.setMaterialSubject(data.getMaterialSubject());
                material.setMaterialDescription(data.getMaterialDescription());
                material.setUpdatedBy(principalService.getPrincipal().getId());
                material.setIsActive(data.getIsActive());
                material = materialDao.update(material);

                if(data.getFile()!=null){
                    attachmentDao.deleteByMaterial(material.getId());
                    for(int i=0; i<data.getFile().size(); i++){
                        File file = new File();
                        file.setFileCode(data.getFile().get(i).getFileCode());
                        file.setFileExt(data.getFile().get(i).getFileExt());
                        file.setCreatedBy(principalService.getPrincipal().getId());
                        file = fileDao.insert(file);
                        Attachment attachment = new Attachment();
                        attachment.setMaterial(material);
                        attachment.setFile(file);
                        attachment.setCreatedBy(principalService.getPrincipal().getId());
                        attachment = attachmentDao.insert(attachment);
                    }
                }

                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.SCHEDULE.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public ScheduleListDataDto getAllByUser(final Long activityId, final Long classHdrId) {
        final List<ScheduleDataDto> responseDb = new ArrayList<>();
        final List<Schedule> find = scheduleDao.getAllByUser(activityId, classHdrId);
        for (int i = 0; i < find.size(); i++) {
            final Schedule schedule = find.get(i);
            final ScheduleDataDto result = new ScheduleDataDto();
            result.setId(schedule.getId());

            result.setStartTime(schedule.getStartTime());
            result.setEndTime(schedule.getEndTime());
            result.setMaterialId(schedule.getMaterial().getId());
            result.setMaterialCode(schedule.getMaterial().getMaterialCode());
            result.setMaterialSubject(schedule.getMaterial().getMaterialSubject());
            result.setMaterialDescription(schedule.getMaterial().getMaterialDescription());

            result.setActivityId(schedule.getMaterial().getActivity().getId());
            result.setActivityCode(schedule.getMaterial().getActivity().getCode());
            result.setActivityType(schedule.getMaterial().getActivity().getType());

            result.setVer(schedule.getVer());
            result.setIsActive(schedule.getIsActive());
            responseDb.add(result);
        }
        final ScheduleListDataDto responseBe = new ScheduleListDataDto();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
