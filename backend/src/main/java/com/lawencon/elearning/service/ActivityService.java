package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.dao.ActivityDao;
import com.lawencon.elearning.dto.activity.ActivityDataDto;
import com.lawencon.elearning.dto.activity.ActivityInsertReqDto;
import com.lawencon.elearning.dto.activity.ActivityListDataDto;
import com.lawencon.elearning.dto.activity.ActivityUpdateReqDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.DeleteResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.model.Activity;
import com.lawencon.elearning.security.PrincipalService;
import com.lawencon.elearning.util.GenerateCodeUtil;

@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private GenerateCodeUtil generateCodeUtil;

    @Autowired
    private PrincipalService principalService;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final ActivityInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final Activity activity = new Activity();
            activity.setCode("AC" + generateCodeUtil.generateDigit(3));
            activity.setType(data.getActivityType());
            activity.setCreatedBy(principalService.getPrincipal().getId());
            final Activity insertOne = activityDao.insert(activity);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.ACTIVITY.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final ActivityUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Activity> optional = activityDao.getById(data.getId());
        Activity updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setType(data.getActivityType());
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = activityDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.ACTIVITY.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public DataResDto<ActivityDataDto> getById(final Long id) {
        final Optional<Activity> optional = activityDao.getById(id);
        Activity findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final ActivityDataDto responseDb = new ActivityDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setActivityCode(findOne.getCode());
            responseDb.setActivityType(findOne.getType());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<ActivityDataDto> responseBe = new DataResDto<ActivityDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.ACTIVITY.getResponse() + " not found!");
        }
    }

    public ActivityListDataDto getAll() {
        final List<ActivityDataDto> responseDb = new ArrayList<>();
        final List<Activity> find = activityDao.getAll();
        for (int i = 0; i < find.size(); i++) {
            final Activity activity = find.get(i);
            final ActivityDataDto result = new ActivityDataDto();
            result.setId(activity.getId());
            result.setActivityCode(activity.getCode());
            result.setActivityType(activity.getType());
            result.setVer(activity.getVer());
            result.setIsActive(activity.getIsActive());
            responseDb.add(result);
        }
        final ActivityListDataDto responseBe = new ActivityListDataDto();
        responseBe.setData(responseDb);
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public DeleteResDto deleteById(final Long id) {
        final DeleteResDto responseBe = new DeleteResDto();
        final Optional<Activity> optional = activityDao.getById(id);
        if (optional.isPresent()) {
            try {
                activityDao.deleteById(id);
                responseBe.setMessage(ResponseConst.DELETED.getResponse() + " " + ModelConst.ACTIVITY.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException(ModelConst.ACTIVITY.getResponse() + " not found!");
        }
        return responseBe;
    }

    public DataResDto<ActivityDataDto> getByCode(final String activityCode) {
        final Optional<Activity> optional = activityDao.getByCode(activityCode);
        Activity findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final ActivityDataDto responseDb = new ActivityDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setActivityCode(findOne.getCode());
            responseDb.setActivityType(findOne.getType());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<ActivityDataDto> responseBe = new DataResDto<ActivityDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.ACTIVITY.getResponse() + " not found!");
        }
    }

}
