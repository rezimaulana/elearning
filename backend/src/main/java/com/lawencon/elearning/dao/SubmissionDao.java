package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Submission;

@Repository
public class SubmissionDao extends BaseDao{
    
    public Submission insert(final Submission data){
        this.em.persist(data);
        return data;
    }

    public Submission update(final Submission data){
        final Submission result = this.em.merge(data);
        return result;
    }

    public Optional<Submission> getById(final Long id){
        final Submission findOne = this.em.find(Submission.class, id);
        em.detach(findOne);
        final Optional<Submission> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Submission> getAllByInstructor(final Long scheduleId, final Long classHdrId) {
        final String sql = "SELECT doc FROM Submission doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "INNER JOIN FETCH schedule.material material "
                + "INNER JOIN FETCH material.activity activity "
                + "INNER JOIN FETCH doc.file file "
                + "WHERE schedule.id = :scheduleId AND hdr.id = :classHdrId ";
        final List<Submission> result = this.em.createQuery(sql, Submission.class)
                .setParameter("scheduleId", scheduleId)
                .setParameter("classHdrId", classHdrId)
                .getResultList();
        return result;
    }

    public List<Submission> getAllByStudent(final Long scheduleId, final Long classDtlId) {
        final String sql = "SELECT doc FROM Submission doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "INNER JOIN FETCH schedule.material material "
                + "INNER JOIN FETCH material.activity activity "
                + "INNER JOIN FETCH doc.file file "
                + "WHERE schedule.id = :scheduleId AND dtl.id = :classDtlId ";
        final List<Submission> result = this.em.createQuery(sql, Submission.class)
                .setParameter("scheduleId", scheduleId)
                .setParameter("classDtlId", classDtlId)
                .getResultList();
        return result;
    }

    //submit once
    public List<Submission> valNotSubmit(final Long classDtlId, final Long scheduleId) {
        final String sql = "SELECT doc FROM Submission doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "WHERE dtl.id = :classDtlId AND schedule.id = :scheduleId ";
        final List<Submission> result = this.em.createQuery(sql, Submission.class)
                .setParameter("classDtlId", classDtlId)
                .setParameter("scheduleId", scheduleId)
                .getResultList();
        return result;
    }

}

