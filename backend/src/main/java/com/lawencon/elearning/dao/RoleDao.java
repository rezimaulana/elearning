package com.lawencon.elearning.dao;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Role;

@Profile("hql")
@Repository
public class RoleDao extends BaseDao{
    
	public Optional<Role> getByCode(final String roleCode) {
		final String sql = "SELECT r FROM Role r WHERE lower(r.roleCode) = lower(:roleCode)";
		final Role findOne = this.em.createQuery(sql, Role.class).setParameter("roleCode", roleCode).getSingleResult();
		final Optional<Role> result = Optional.ofNullable(findOne);
		return result;
	}

}
