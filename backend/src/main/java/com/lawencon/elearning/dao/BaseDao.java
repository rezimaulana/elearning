package com.lawencon.elearning.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
abstract class BaseDao {
    
    @PersistenceContext
    protected EntityManager em;

    public void setEm(@Autowired EntityManager em){
        this.em = em;
    }

}
