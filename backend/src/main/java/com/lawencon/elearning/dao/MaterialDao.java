package com.lawencon.elearning.dao;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Material;

@Profile("hql")
@Repository
public class MaterialDao extends BaseDao {
    
    public Material insert(final Material data){
        this.em.persist(data);
        return data;
    }

    public Material update(final Material data) {
        final Material result = this.em.merge(data);
        return result;
    }

    public Optional<Material> getById(final Long id){
        final Material findOne = this.em.find(Material.class, id);
        em.detach(findOne);
        final Optional<Material> result = Optional.ofNullable(findOne);
        return result;
    }

}
