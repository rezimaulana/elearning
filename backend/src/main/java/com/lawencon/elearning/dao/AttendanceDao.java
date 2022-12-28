package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Attendance;

@Profile("hql")
@Repository
public class AttendanceDao extends BaseDao{
    
    public Attendance insert(final Attendance data){
        this.em.persist(data);
        return data;
    }

    public Attendance update(final Attendance data){
        final Attendance result = this.em.merge(data);
        return result;
    }

    public Optional<Attendance> getById(final Long id){
        final Attendance findOne = this.em.find(Attendance.class, id);
        em.detach(findOne);
        final Optional<Attendance> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Attendance> getAllByInstructor(final Long scheduleId, final Long classHdrId) {
        final String sql = "SELECT doc FROM Attendance doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "INNER JOIN FETCH schedule.material material "
                + "INNER JOIN FETCH material.activity activity "
                + "WHERE schedule.id = :scheduleId AND hdr.id = :classHdrId ";
        final List<Attendance> result = this.em.createQuery(sql, Attendance.class)
                .setParameter("scheduleId", scheduleId)
                .setParameter("classHdrId", classHdrId)
                .getResultList();
        return result;
    }

    public List<Attendance> getAllByStudent(final Long scheduleId, final Long classDtlId) {
        final String sql = "SELECT doc FROM Attendance doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "INNER JOIN FETCH schedule.material material "
                + "INNER JOIN FETCH material.activity activity "
                + "WHERE schedule.id = :scheduleId AND dtl.id = :classDtlId ";
        final List<Attendance> result = this.em.createQuery(sql, Attendance.class)
                .setParameter("scheduleId", scheduleId)
                .setParameter("classDtlId", classDtlId)
                .getResultList();
        return result;
    }

    public List<Attendance> valNotAttend(final Long classDtlId, final Long scheduleId) {
        final String sql = "SELECT doc FROM Attendance doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "WHERE dtl.id = :classDtlId AND schedule.id = :scheduleId ";
        final List<Attendance> result = this.em.createQuery(sql, Attendance.class)
                .setParameter("classDtlId", classDtlId)
                .setParameter("scheduleId", scheduleId)
                .getResultList();
        return result;
    }

    public List<Attendance> valAttendance(final Long scheduleId, final Long classDtlId) {
        final String sql = "SELECT doc FROM Attendance doc "
                + "INNER JOIN FETCH doc.classDtl dtl "
                + "INNER JOIN FETCH dtl.student std "
                + "INNER JOIN FETCH dtl.classHdr hdr "
                + "INNER JOIN FETCH hdr.instructor ins "
                + "INNER JOIN FETCH doc.schedule schedule "
                + "INNER JOIN FETCH schedule.material material "
                + "INNER JOIN FETCH material.activity activity "
                + "WHERE schedule.id = :scheduleId AND dtl.id = :classDtlId AND doc.approval = TRUE ";
        final List<Attendance> result = this.em.createQuery(sql, Attendance.class)
                .setParameter("scheduleId", scheduleId)
                .setParameter("classDtlId", classDtlId)
                .getResultList();
        return result;
    }

}
