package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Activity;

@Profile("hql")
@Repository
public class ActivityDao extends BaseDao{
    
    public Activity insert(final Activity data){
        this.em.persist(data);
        return data;
    }

    public Activity update(final Activity data){
        final Activity result = this.em.merge(data);
        return result;
    }

    public Optional<Activity> getById(final Long id){
        final Activity findOne = this.em.find(Activity.class, id);
        em.detach(findOne);
        final Optional<Activity> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Activity> getAll(){
        final String sql = "SELECT doc FROM Activity doc";
        final List<Activity> result = this.em.createQuery(sql, Activity.class).getResultList();
        return result;
    }

    public boolean deleteById(final Long id){
        final String sql = "DELETE FROM Activity WHERE :id = id";
        final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();
        return result > 0;
    }

    public Optional<Activity> getByCode(final String activityCode) {
		final String sql = "SELECT doc FROM Activity doc WHERE lower(doc.code) = lower(:activityCode)";
		final Activity findOne = this.em.createQuery(sql, Activity.class).setParameter("activityCode", activityCode).getSingleResult();
		final Optional<Activity> result = Optional.ofNullable(findOne);
		return result;
	}

}
