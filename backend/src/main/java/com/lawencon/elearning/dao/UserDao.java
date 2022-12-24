package com.lawencon.elearning.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.User;

@Repository
@Profile("hql")
public class UserDao extends BaseDao {

    public Optional<User> getByEmail(final String email)  {		
		final String sql = " SELECT u "
				+ "FROM User u "
				+ "INNER JOIN FETCH u.role r "
				+ "WHERE u.email = :email";
		final User findOne = this.em.createQuery(sql, User.class).setParameter("email", email).getSingleResult();
		final Optional<User> result = Optional.ofNullable(findOne);
		return result;
	}

	public User insert(final User data){
        this.em.persist(data);
        return data;
    }

    public User update(final User data){
        final User result = this.em.merge(data);
        return result;
    }

	public Optional<User> getById(final Long id) {
		final User findOne = this.em.find(User.class, id);
		em.detach(findOne);
		final Optional<User> result = Optional.ofNullable(findOne);
		return result;
	}

	public List<User> getAll(){
        final String sql = "SELECT doc FROM User doc";
        final List<User> result = this.em.createQuery(sql, User.class).getResultList();
        return result;
    }

}
