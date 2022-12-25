package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.ClassDtl;

@Repository
public class ClassDtlDao extends BaseDao {
    
    public ClassDtl insert(final ClassDtl data){
        this.em.persist(data);
        return data;
    }

    public Optional<ClassDtl> getById(final Long id) {
        final ClassDtl findOne = this.em.find(ClassDtl.class, id);
        em.detach(findOne);
        final Optional<ClassDtl> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<ClassDtl> getAllByStudent(final Long id){
        final String sql = "SELECT doc FROM ClassDtl doc "
            + "INNER JOIN FETCH doc.student std "
            + "INNER JOIN FETCH doc.classHdr hdr "
            + "INNER JOIN FETCH hdr.instructor ins "
            + "INNER JOIN FETCH hdr.photo file "
            + "WHERE std.id = :id ";
        final List<ClassDtl> result = this.em.createQuery(sql, ClassDtl.class).setParameter("id", id).getResultList();
        return result;
    }

}
