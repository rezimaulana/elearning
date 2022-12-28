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
import com.lawencon.elearning.dao.CommentDao;
import com.lawencon.elearning.dao.ForumDao;
import com.lawencon.elearning.dto.comment.CommentDataDto;
import com.lawencon.elearning.dto.forum.ForumDataDto;
import com.lawencon.elearning.dto.forum.ForumInsertReqDto;
import com.lawencon.elearning.dto.forum.ForumUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.model.ClassDtl;
import com.lawencon.elearning.model.Comment;
import com.lawencon.elearning.model.Forum;
import com.lawencon.elearning.security.PrincipalService;

@Service
public class ForumService {
    
    @Autowired
    private ForumDao forumDao;

    @Autowired
    private ClassDtlDao classDtlDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private CommentDao commentDao;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final ForumInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final Forum forum = new Forum();
            forum.setTitle(data.getTitle());
            forum.setContent(data.getContent());
            final Optional<ClassDtl> optionalFk1 = classDtlDao.getById(data.getClassDtlId());
            ClassDtl dtl = optionalFk1.get();
            forum.setClassDtl(dtl);
            forum.setCreatedBy(principalService.getPrincipal().getId());
            final Forum insertOne = forumDao.insert(forum);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.FORUM.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final ForumUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Forum> optional = forumDao.getById(data.getId());
        Forum updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setTitle(data.getTitle());
                updateOne.setContent(data.getContent());
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = forumDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.FORUM.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

    public DataResDto<ForumDataDto> getById(final Long id) {
        final Optional<Forum> optional = forumDao.getById(id);
        Forum findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final ForumDataDto responseDb = new ForumDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setTitle(findOne.getTitle());
            responseDb.setContent(findOne.getContent());
            responseDb.setClassDtlId(findOne.getClassDtl().getId());
            responseDb.setStdId(findOne.getClassDtl().getStudent().getId());
            responseDb.setStdEmail(findOne.getClassDtl().getStudent().getEmail());
            responseDb.setStdFullname(findOne.getClassDtl().getStudent().getFullname());
            responseDb.setClassHdrId(findOne.getClassDtl().getClassHdr().getId());
            responseDb.setInsId(findOne.getClassDtl().getClassHdr().getInstructor().getId());
            responseDb.setInsEmail(findOne.getClassDtl().getClassHdr().getInstructor().getEmail());
            responseDb.setInsFullname(findOne.getClassDtl().getClassHdr().getInstructor().getFullname());
            responseDb.setCreatedAt(findOne.getCreatedAt());
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());

            final List<Comment> comment = commentDao.getAllByUser(findOne.getId());
            if(comment!=null){
                final List<CommentDataDto> commentDto = new ArrayList<>();
                for(int i = 0; i<comment.size(); i++){
                    final CommentDataDto commentRow = new CommentDataDto();
                    commentRow.setId(comment.get(i).getId());
                    commentRow.setComment(comment.get(i).getContent());
                    commentRow.setUserId(comment.get(i).getUser().getId());
                    commentRow.setUserEmail(comment.get(i).getUser().getEmail());
                    commentRow.setUserFullname(comment.get(i).getUser().getFullname());
                    if(comment.get(i).getUser().getPhoto()!=null){
                        commentRow.setFileId(comment.get(i).getUser().getPhoto().getId());
                    }
                    commentRow.setIsActive(comment.get(i).getIsActive());
                    commentRow.setVer(comment.get(i).getVer());
                    commentDto.add(commentRow);
                }
                responseDb.setComment(commentDto);
            }

            final DataResDto<ForumDataDto> responseBe = new DataResDto<ForumDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.FORUM.getResponse() + " not found!");
        }
    }

    public DataListResDto<ForumDataDto> getAllByInstructor(final Long classHdrId) {
        final List<ForumDataDto> responseDb = new ArrayList<>();
        final List<Forum> find = forumDao.getAllByInstructor(classHdrId);
        for (int i = 0; i < find.size(); i++) {
            final Forum forum = find.get(i);
            final ForumDataDto result = new ForumDataDto();
            result.setId(forum.getId());
            result.setTitle(forum.getTitle());
            result.setContent(forum.getContent());
            result.setClassDtlId(forum.getClassDtl().getId());
            result.setStdId(forum.getClassDtl().getStudent().getId());
            result.setStdEmail(forum.getClassDtl().getStudent().getEmail());
            result.setStdFullname(forum.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(forum.getClassDtl().getClassHdr().getId());
            result.setInsId(forum.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(forum.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(forum.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setCreatedAt(forum.getCreatedAt());
            result.setVer(forum.getVer());
            result.setIsActive(forum.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<ForumDataDto> responseBe = new DataListResDto<ForumDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

    public DataListResDto<ForumDataDto> getAllByStudent(final Long classHdrId) {
        final List<ForumDataDto> responseDb = new ArrayList<>();
        final List<Forum> find = forumDao.getAllByStudent(classHdrId);
        for (int i = 0; i < find.size(); i++) {
            final Forum forum = find.get(i);
            final ForumDataDto result = new ForumDataDto();
            result.setId(forum.getId());
            result.setTitle(forum.getTitle());
            result.setContent(forum.getContent());
            result.setClassDtlId(forum.getClassDtl().getId());
            result.setStdId(forum.getClassDtl().getStudent().getId());
            result.setStdEmail(forum.getClassDtl().getStudent().getEmail());
            result.setStdFullname(forum.getClassDtl().getStudent().getFullname());
            result.setClassHdrId(forum.getClassDtl().getClassHdr().getId());
            result.setInsId(forum.getClassDtl().getClassHdr().getInstructor().getId());
            result.setInsEmail(forum.getClassDtl().getClassHdr().getInstructor().getEmail());
            result.setInsFullname(forum.getClassDtl().getClassHdr().getInstructor().getFullname());
            result.setCreatedAt(forum.getCreatedAt());
            result.setVer(forum.getVer());
            result.setIsActive(forum.getIsActive());
            responseDb.add(result);
        }
        final DataListResDto<ForumDataDto> responseBe = new DataListResDto<ForumDataDto>();
        responseBe.setData(responseDb);
        return responseBe;
    }

}
