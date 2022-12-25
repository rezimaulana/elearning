package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.ClassHdr;

@Profile("hql")
@Repository
public class ClassHdrDao extends BaseDao {
    
    public ClassHdr insert(final ClassHdr data){
        this.em.persist(data);
        return data;
    }

    public ClassHdr update(final ClassHdr data){
        final ClassHdr result = this.em.merge(data);
        return result;
    }

    public Optional<ClassHdr> getById(final Long id) {
        final ClassHdr findOne = this.em.find(ClassHdr.class, id);
        em.detach(findOne);
        final Optional<ClassHdr> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<ClassHdr> getAll(){
        final String sql = "SELECT doc FROM ClassHdr doc "
            + "INNER JOIN FETCH doc.instructor ins "
            + "INNER JOIN FETCH doc.photo file ";
        final List<ClassHdr> result = this.em.createQuery(sql, ClassHdr.class).getResultList();
        return result;
    }

    public List<ClassHdr> getAllByInstructor(final Long id){
        final String sql = "SELECT doc FROM ClassHdr doc "
            + "INNER JOIN FETCH doc.instructor ins "
            + "INNER JOIN FETCH doc.photo file "
            + "WHERE ins.id = :id ";
        final List<ClassHdr> result = this.em.createQuery(sql, ClassHdr.class).setParameter("id", id).getResultList();
        return result;
    }

}
