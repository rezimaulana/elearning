package com.lawencon.elearning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.FileDao;
import com.lawencon.elearning.model.File;

@Service
public class FileService {
    
    @Autowired
    private FileDao fileDao;

    public Optional<File> getById(final Long id) {
		return fileDao.getById(id);
	}

}
