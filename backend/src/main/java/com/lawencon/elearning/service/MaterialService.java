package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.dao.AttachmentDao;
import com.lawencon.elearning.dao.AttendanceDao;
import com.lawencon.elearning.dao.ScheduleDao;
import com.lawencon.elearning.dto.material.MaterialDataDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.model.Attachment;
import com.lawencon.elearning.model.Schedule;

@Service
public class MaterialService {
    
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private AttendanceDao attendaceDao;

    public DataResDto<MaterialDataDto> getById(final Long scheduleId, final Long classDtlId) {
        final Optional<Schedule> optional = scheduleDao.getById(scheduleId);

        if(classDtlId!=null){
            if(attendaceDao.valAttendance(scheduleId, classDtlId).size() != 1) {
                throw new RuntimeException("You have to fill in the attendance first!");
            }
        }

        Schedule findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final MaterialDataDto responseDb = new MaterialDataDto();
            responseDb.setId(findOne.getId());

            responseDb.setStartTime(findOne.getStartTime());
            responseDb.setEndTime(findOne.getEndTime());
            responseDb.setMaterialId(findOne.getMaterial().getId());
            responseDb.setMaterialCode(findOne.getMaterial().getMaterialCode());
            responseDb.setMaterialSubject(findOne.getMaterial().getMaterialSubject());
            responseDb.setMaterialDescription(findOne.getMaterial().getMaterialDescription());

            responseDb.setActivityId(findOne.getMaterial().getActivity().getId());
            responseDb.setActivityCode(findOne.getMaterial().getActivity().getCode());
            responseDb.setActivityType(findOne.getMaterial().getActivity().getType());

            final List<Attachment> file = attachmentDao.getAllByMaterial(findOne.getMaterial().getId());
            if(file!=null){
                final List<Long> fileId = new ArrayList<>(); 
                for(int i = 0; i<file.size(); i++){
                    fileId.add(file.get(i).getFile().getId());
                }
                responseDb.setFile(fileId);
            }

            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<MaterialDataDto> responseBe = new DataResDto<MaterialDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.ACTIVITY.getResponse() + " not found!");
        }
    }

}
