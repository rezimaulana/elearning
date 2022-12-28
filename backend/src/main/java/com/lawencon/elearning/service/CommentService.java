package com.lawencon.elearning.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.dao.CommentDao;
import com.lawencon.elearning.dao.ForumDao;
import com.lawencon.elearning.dto.comment.CommentInsertReqDto;
import com.lawencon.elearning.dto.comment.CommentUpdateReqDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.model.Comment;
import com.lawencon.elearning.model.Forum;
import com.lawencon.elearning.security.PrincipalService;

@Service
public class CommentService {
    
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private ForumDao forumDao;

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<InsertResDto> insert(final CommentInsertReqDto data) {
        final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
        try {
            final Comment comment = new Comment();
            comment.setContent(data.getComment());
            final Optional<Forum> optionalFk1 = forumDao.getById(data.getForumId());
            Forum forum = optionalFk1.get();
            comment.setForum(forum);
            comment.setUser(principalService.getPrincipal());
            comment.setCreatedBy(principalService.getPrincipal().getId());
            final Comment insertOne = commentDao.insert(comment);
            final InsertResDto responseDb = new InsertResDto();
            responseDb.setId(insertOne.getId());
            responseBe.setData(responseDb);
            responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.COMMENT.getResponse());
        } catch (Exception e) {
            responseBe.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return responseBe;
    }

    @Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final CommentUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<Comment> optional = commentDao.getById(data.getId());
        Comment updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
                updateOne.setContent(data.getComment());
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = commentDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.COMMENT.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }

}
