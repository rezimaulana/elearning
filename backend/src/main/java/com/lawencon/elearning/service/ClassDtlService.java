package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.dao.ClassDtlDao;
import com.lawencon.elearning.dao.ClassHdrDao;
import com.lawencon.elearning.dao.UserDao;
import com.lawencon.elearning.dto.class_dtl.ClassDtlDataDto;
import com.lawencon.elearning.dto.class_dtl.ClassDtlInsertReqDto;
import com.lawencon.elearning.dto.class_dtl.ClassDtlListDataDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.model.ClassDtl;
import com.lawencon.elearning.model.ClassHdr;
import com.lawencon.elearning.model.User;
import com.lawencon.elearning.security.PrincipalService;

@Service
public class ClassDtlService {
    
    @Autowired
    private ClassDtlDao classDtlDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClassHdrDao classHdrDao;

    @Autowired
    private PrincipalService principalService;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final ClassDtlInsertReqDto data){
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final ClassDtl classDtl = new ClassDtl();
            final Optional<User> optional = userDao.getById(principalService.getPrincipal().getId());
            classDtl.setStudent(optional.get());
            final Optional<ClassHdr> optionalFk = classHdrDao.getById(data.getClassHdrId());
            if(optional.isPresent()){
                classDtl.setClassHdr(optionalFk.get());
            } else {
                throw new RuntimeException(ModelConst.CLASS_HDR.getResponse() + " not found!");
            }
            classDtl.setCreatedBy(principalService.getPrincipal().getId());
            final ClassDtl insertOne = classDtlDao.insert(classDtl);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.CLASS_DTL.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    public DataResDto<ClassDtlDataDto> getById(final Long id) {
        final Optional<ClassDtl> optional = classDtlDao.getById(id);
        ClassDtl findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final ClassDtlDataDto responseDb = new ClassDtlDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setStdId(findOne.getStudent().getId());
            responseDb.setStdEmail(findOne.getStudent().getEmail());
            responseDb.setStdFullname(findOne.getStudent().getFullname());
            responseDb.setClassHdrId(findOne.getClassHdr().getId());
            responseDb.setClassHdrCode(findOne.getClassHdr().getClassHdrCode());
            responseDb.setClassHdrSubject(findOne.getClassHdr().getClassHdrSubject());
            responseDb.setClassHdrDescription(findOne.getClassHdr().getClassHdrDescription());
            responseDb.setInsId(findOne.getClassHdr().getInstructor().getId());
            responseDb.setInsEmail(findOne.getClassHdr().getInstructor().getEmail());
            responseDb.setInsFullname(findOne.getClassHdr().getInstructor().getFullname());
            responseDb.setFileId(findOne.getClassHdr().getPhoto().getId());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<ClassDtlDataDto> responseBe = new DataResDto<ClassDtlDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.CLASS_HDR.getResponse() + " not found!");
        }
    }

    public ClassDtlListDataDto getAllByStudent() {
        final List<ClassDtlDataDto> responseDb = new ArrayList<>();
        final List<ClassDtl> find = classDtlDao.getAllByStudent(principalService.getPrincipal().getId());
        for (int i = 0; i < find.size(); i++) {
            final ClassDtl classDtl = find.get(i);
            final ClassDtlDataDto result = new ClassDtlDataDto();
            result.setId(classDtl.getId());
            result.setStdId(classDtl.getStudent().getId());
            result.setStdEmail(classDtl.getStudent().getEmail());
            result.setStdFullname(classDtl.getStudent().getFullname());
            result.setClassHdrId(classDtl.getClassHdr().getId());
            result.setClassHdrCode(classDtl.getClassHdr().getClassHdrCode());
            result.setClassHdrSubject(classDtl.getClassHdr().getClassHdrSubject());
            result.setClassHdrDescription(classDtl.getClassHdr().getClassHdrDescription());
            result.setInsId(classDtl.getClassHdr().getInstructor().getId());
            result.setInsEmail(classDtl.getClassHdr().getInstructor().getEmail());
            result.setInsFullname(classDtl.getClassHdr().getInstructor().getFullname());
            result.setFileId(classDtl.getClassHdr().getPhoto().getId());
            result.setVer(classDtl.getVer());
            result.setIsActive(classDtl.getIsActive());
            responseDb.add(result);
        }
        final ClassDtlListDataDto responseBe = new ClassDtlListDataDto();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
