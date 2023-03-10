package com.lawencon.elearning.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Attachment;

@Profile("hql")
@Repository
public class AttachmentDao extends BaseDao {
    
    public Attachment insert(final Attachment data){
        this.em.persist(data);
        return data;
    }

    public boolean deleteByMaterial(final Long materialId){
        final String sql = "DELETE FROM Attachment WHERE material.id = :materialId ";
        final int result = this.em.createQuery(sql).setParameter("materialId", materialId).executeUpdate();
        return result > 0;
    }

    public List<Attachment> getAllByMaterial(final Long materialId) {
        final String sql = "SELECT doc FROM Attachment doc "
                + "INNER JOIN FETCH doc.material material "
                + "WHERE material.id = :materialId ";
        final List<Attachment> result = this.em.createQuery(sql, Attachment.class)
                .setParameter("materialId", materialId)
                .getResultList();
        return result;
    }

}
