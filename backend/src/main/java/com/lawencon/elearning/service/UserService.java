package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.UserDao;
import com.lawencon.elearning.model.User;
import com.lawencon.elearning.security.PrincipalService;
import com.lawencon.elearning.util.GenerateCodeUtil;
import com.lawencon.elearning.util.JavaMailUtil;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
	private GenerateCodeUtil generateCodeUtil;

	@Autowired
	private JavaMailUtil mailUtil;

	@Autowired
	private PrincipalService principalService;

    @Autowired
	private PasswordEncoder passwordEncode;

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userDao.getByEmail(username);
		if(optional.isPresent()) {
			return new org.springframework.security.core
					.userdetails.User(username, optional.get().getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Wrong Email or Password!");
	}

	public Optional<User> getByEmail(final String email) {
		return userDao.getByEmail(email);
	}



}
