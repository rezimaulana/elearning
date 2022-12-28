package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Schedule;

@Profile("hql")
@Repository
public class ScheduleDao extends BaseDao {

    public Schedule insert(final Schedule data) {
        this.em.persist(data);
        return data;
    }

    public Schedule update(final Schedule data) {
        final Schedule result = this.em.merge(data);
        return result;
    }

    public Optional<Schedule> getById(final Long id){
        final Schedule findOne = this.em.find(Schedule.class, id);
        em.detach(findOne);
        final Optional<Schedule> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Schedule> getAllByUser(final Long activityId, final Long classHdrId) {
        final String sql = "SELECT doc FROM Schedule doc "
                + "INNER JOIN FETCH doc.material material "
                + "INNER JOIN FETCH material.classHdr hdr "
                + "INNER JOIN FETCH material.activity activity "
                + "WHERE activity.id = :activityId AND hdr.id = :classHdrId ";
        final List<Schedule> result = this.em.createQuery(sql, Schedule.class)
                .setParameter("activityId", activityId)
                .setParameter("classHdrId", classHdrId)
                .getResultList();
        return result;
    }

}
