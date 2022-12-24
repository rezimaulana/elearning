package com.lawencon.elearning.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.UserDao;
import com.lawencon.elearning.model.User;

@Service
public class PrincipalService{
	
	@Autowired
	private UserDao userDao;

	public User getPrincipal() {
		long id = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		Optional<User> optional = userDao.getById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new RuntimeException("Invalid Login!");
	}
	
}
