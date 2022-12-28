package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Comment;

@Profile("hql")
@Repository
public class CommentDao extends BaseDao{
    
    public Comment insert(final Comment data){
        this.em.persist(data);
        return data;
    }

    public Comment update(final Comment data){
        final Comment result = this.em.merge(data);
        return result;
    }

    public Optional<Comment> getById(final Long id){
        final Comment findOne = this.em.find(Comment.class, id);
        em.detach(findOne);
        final Optional<Comment> result = Optional.ofNullable(findOne);
        return result;
    }

    public List<Comment> getAllByUser(final Long forumId) {
        final String sql = "SELECT doc FROM Comment doc "
                + "INNER JOIN FETCH doc.forum forum "
                + "INNER JOIN FETCH doc.user user "
                + "LEFT JOIN FETCH user.photo file "
                + "WHERE forum.id = :forumId "
                + "ORDER BY doc.createdAt DESC ";
        final List<Comment> result = this.em.createQuery(sql, Comment.class)
                .setParameter("forumId", forumId)
                .getResultList();
        return result;
    }

}
