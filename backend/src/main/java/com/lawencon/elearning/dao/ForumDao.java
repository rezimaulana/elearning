package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Forum;

@Repository
public class ForumDao extends BaseDao {
    
    public Forum insert(final Forum data){
        this.em.persist(data);
        return data;
    }

    public Forum update(final Forum data){
        final Forum result = this.em.merge(data);
        return result;
    }

    public Optional<Forum> getById(final Long id){
        final Forum findOne = this.em.find(Forum.class, id);
        em.detach(findOne);
        final Optional<Forum> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Forum> getAllByInstructor(final Long classHdrId) {
        final String sql = "SELECT doc FROM Forum doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "WHERE hdr.id = :classHdrId ";
        final List<Forum> result = this.em.createQuery(sql, Forum.class)
                .setParameter("classHdrId", classHdrId)
                .getResultList();
        return result;
    }

    public List<Forum> getAllByStudent(final Long classDtlId) {
        final String sql = "SELECT doc FROM Forum doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "WHERE dtl.id = :classDtlId ";
        final List<Forum> result = this.em.createQuery(sql, Forum.class)
                .setParameter("classDtlId", classDtlId)
                .getResultList();
        return result;
    }

}
