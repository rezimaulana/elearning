package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.constant.RoleConst;
import com.lawencon.elearning.dao.ClassHdrDao;
import com.lawencon.elearning.dao.FileDao;
import com.lawencon.elearning.dao.UserDao;
import com.lawencon.elearning.dto.class_hdr.ClassHdrDataDto;
import com.lawencon.elearning.dto.class_hdr.ClassHdrInsertReqDto;
import com.lawencon.elearning.dto.class_hdr.ClassHdrUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.model.ClassHdr;
import com.lawencon.elearning.model.File;
import com.lawencon.elearning.model.User;
import com.lawencon.elearning.security.PrincipalService;
import com.lawencon.elearning.util.GenerateCodeUtil;

@Service
public class ClassHdrService {
    
    @Autowired
    private ClassHdrDao classHdrDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private GenerateCodeUtil generateCodeUtil;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final ClassHdrInsertReqDto data){
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final ClassHdr classHdr = new ClassHdr();
            classHdr.setClassHdrCode("CLASS"+generateCodeUtil.generateDigit(5));
            classHdr.setClassHdrSubject(data.getClassHdrSubject());
            classHdr.setClassHdrDescription(data.getClassHdrDescription());
            final Optional<User> optional = userDao.getById(data.getInsId());
            if(optional.isPresent()){
                if(optional.get().getRole().getRoleCode().equals(RoleConst.INSTRUCTOR.getRoleCode())){
                    classHdr.setInstructor(optional.get());
                } else {
                    throw new RuntimeException(ModelConst.USER.getResponse() + " role not valid!");
                }
            } else {
                throw new RuntimeException(ModelConst.USER.getResponse() + " not found!");
            }
            File file = new File();
            file.setFileCode(data.getFileCode());
            file.setFileExt(data.getFileExt());
            file.setCreatedBy(principalService.getPrincipal().getId());
            file = fileDao.insert(file);
            classHdr.setPhoto(file);
            classHdr.setCreatedBy(principalService.getPrincipal().getId());
            final ClassHdr insertOne = classHdrDao.insert(classHdr);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.CLASS_HDR.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final ClassHdrUpdateReqDto data){
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<ClassHdr> optional = classHdrDao.getById(data.getId());
        ClassHdr updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setClassHdrSubject(data.getClassHdrSubject());
                updateOne.setClassHdrDescription(data.getClassHdrDescription());
                final Optional<User> user = userDao.getById(data.getInsId());
                if(user.isPresent()){
                    if(user.get().getRole().getRoleCode().equals(RoleConst.INSTRUCTOR.getRoleCode())){
                        updateOne.setInstructor(user.get());
                    } else {
                        throw new RuntimeException(ModelConst.USER.getResponse() + " role not valid!");
                    }
                } else {
                    throw new RuntimeException(ModelConst.USER.getResponse() + " not found!");
                }
                if(data.getFileCode()!=null && data.getFileExt()!=null){
                    File file = new File();
                    file.setFileCode(data.getFileCode());
                    file.setFileExt(data.getFileExt());
                    file.setCreatedBy(principalService.getPrincipal().getId());
                    file = fileDao.insert(file);
                    updateOne.setPhoto(file);
                }
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = classHdrDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.CLASS_HDR.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public DataResDto<ClassHdrDataDto> getById(final Long id) {
        final Optional<ClassHdr> optional = classHdrDao.getById(id);
        ClassHdr findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final ClassHdrDataDto responseDb = new ClassHdrDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setClassHdrCode(findOne.getClassHdrCode());
            responseDb.setClassHdrSubject(findOne.getClassHdrSubject());
            responseDb.setClassHdrDescription(findOne.getClassHdrDescription());
            responseDb.setInsId(findOne.getInstructor().getId());
            responseDb.setInsEmail(findOne.getInstructor().getEmail());
            responseDb.setInsFullname(findOne.getInstructor().getFullname());
            responseDb.setFileId(findOne.getPhoto().getId());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<ClassHdrDataDto> responseBe = new DataResDto<ClassHdrDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.CLASS_HDR.getResponse() + " not found!");
        }
    }

    public DataListResDto<ClassHdrDataDto> getAll() {
        final List<ClassHdrDataDto> responseDb = new ArrayList<>();
        final List<ClassHdr> find = classHdrDao.getAll();
        for (int i = 0; i < find.size(); i++) {
            final ClassHdr classHdr = find.get(i);
            final ClassHdrDataDto result = new ClassHdrDataDto();
            result.setId(classHdr.getId());
            result.setClassHdrCode(classHdr.getClassHdrCode());
            result.setClassHdrSubject(classHdr.getClassHdrSubject());
            result.setClassHdrDescription(classHdr.getClassHdrDescription());
            result.setInsId(classHdr.getInstructor().getId());
            result.setInsEmail(classHdr.getInstructor().getEmail());
            result.setInsFullname(classHdr.getInstructor().getFullname());
            result.setFileId(classHdr.getPhoto().getId());
            result.setVer(classHdr.getVer());
            result.setIsActive(classHdr.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<ClassHdrDataDto> responseBe = new DataListResDto<ClassHdrDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

    public DataListResDto<ClassHdrDataDto> getAllByInstructor() {
        final List<ClassHdrDataDto> responseDb = new ArrayList<>();
        final List<ClassHdr> find = classHdrDao.getAllByInstructor(principalService.getPrincipal().getId());
        for (int i = 0; i < find.size(); i++) {
            final ClassHdr classHdr = find.get(i);
            final ClassHdrDataDto result = new ClassHdrDataDto();
            result.setId(classHdr.getId());
            result.setClassHdrCode(classHdr.getClassHdrCode());
            result.setClassHdrSubject(classHdr.getClassHdrSubject());
            result.setClassHdrDescription(classHdr.getClassHdrDescription());
            result.setInsId(classHdr.getInstructor().getId());
            result.setInsEmail(classHdr.getInstructor().getEmail());
            result.setInsFullname(classHdr.getInstructor().getFullname());
            result.setFileId(classHdr.getPhoto().getId());
            result.setVer(classHdr.getVer());
            result.setIsActive(classHdr.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<ClassHdrDataDto> responseBe = new DataListResDto<ClassHdrDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
