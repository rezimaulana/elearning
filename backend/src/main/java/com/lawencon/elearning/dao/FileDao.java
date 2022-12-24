package com.lawencon.elearning.dao;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.File;

@Repository
@Profile("hql")
public class FileDao extends BaseDao {
    
    public File insert(final File data) {
		this.em.persist(data);
		return data;
	}

    public Optional<File> getById(final Long id){
        final File findOne = this.em.find(File.class, id);
        em.detach(findOne);
        final Optional<File> result = Optional.ofNullable(findOne);
        return result;
    }

}
