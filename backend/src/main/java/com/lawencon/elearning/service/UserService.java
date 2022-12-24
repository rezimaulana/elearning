package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.constant.ModelConst;
import com.lawencon.elearning.constant.ResponseConst;
import com.lawencon.elearning.constant.RoleConst;
import com.lawencon.elearning.dao.FileDao;
import com.lawencon.elearning.dao.RoleDao;
import com.lawencon.elearning.dao.UserDao;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.user.UserDataDto;
import com.lawencon.elearning.dto.user.UserInsertReqDto;
import com.lawencon.elearning.dto.user.UserListDataDto;
import com.lawencon.elearning.dto.user.UserUpdateReqDto;
import com.lawencon.elearning.model.File;
import com.lawencon.elearning.model.Role;
import com.lawencon.elearning.model.User;
import com.lawencon.elearning.pojo.EmailPojo;
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

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private FileDao fileDao;

	private final Long systemId = 1L;

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
	
	private void sendingEmail(final UserInsertReqDto data, final String plainText) {
		final EmailPojo emailPojo = new EmailPojo();
		emailPojo.setEmail(data.getEmail());
		emailPojo.setSubject("Registration Success");
		emailPojo.setBody("Email : "+data.getEmail()+"\nYour Password : " + plainText);
		Runnable r = () -> mailUtil.sendEmail(emailPojo);
		Thread t = new Thread(r);
		t.start();
	}

	@Transactional(rollbackOn = Exception.class)
	public TransactionResDto<InsertResDto> insertStudent(UserInsertReqDto data) {
		final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
			final String plainText = generateCodeUtil.generateDigit(5);
			final String hash = passwordEncode.encode(plainText);
			final User user = new User();
			user.setEmail(data.getEmail());
			user.setFullname(data.getFullname());
			user.setPassword(hash);
			final Optional<Role> role = roleDao.getByCode(RoleConst.STUDENT.getRoleCode());
			user.setRole(role.get());
			user.setCreatedBy(systemId);
			final User insertOne = userDao.insert(user);
			sendingEmail(data, plainText);
			final InsertResDto responseDb = new InsertResDto();
			responseDb.setId(insertOne.getId());
			responseBe.setData(responseDb);
			responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.USER.getResponse());
		} catch (Exception e) {
			responseBe.setMessage(e.getMessage());
            e.printStackTrace();
		}
		return responseBe;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public TransactionResDto<InsertResDto> insertInstructor(UserInsertReqDto data) {
		final TransactionResDto<InsertResDto> responseBe = new TransactionResDto<InsertResDto>();
		try {
			final String plainText = generateCodeUtil.generateDigit(5);
			final String hash = passwordEncode.encode(plainText);
			final User user = new User();
			user.setEmail(data.getEmail());
			user.setFullname(data.getFullname());
			user.setPassword(hash);
			final Optional<Role> role = roleDao.getByCode(RoleConst.INSTRUCTOR.getRoleCode());
			user.setRole(role.get());
			user.setCreatedBy(principalService.getPrincipal().getId());
			final User insertOne = userDao.insert(user);
			sendingEmail(data, plainText);
			final InsertResDto responseDb = new InsertResDto();
			responseDb.setId(insertOne.getId());
			responseBe.setData(responseDb);
			responseBe.setMessage(ResponseConst.CREATED.getResponse() + " " + ModelConst.USER.getResponse());
		} catch (Exception e) {
			responseBe.setMessage(e.getMessage());
            e.printStackTrace();
		}
		return responseBe;
	}
	
	@Transactional(rollbackOn = Exception.class)
    public TransactionResDto<UpdateResDto> update(final UserUpdateReqDto data) {
        final TransactionResDto<UpdateResDto> responseBe = new TransactionResDto<UpdateResDto>();
        final Optional<User> optional = userDao.getById(data.getId());
        User updateOne = null;
        if (optional.isPresent()) {
            updateOne = optional.get();
            try {
            	if(data.getFullname()!=null) {
            		updateOne.setFullname(data.getFullname());
            	}
            	if(data.getNewPassword()!=null && data.getOldPassword()!=null) {
            		if(updateOne.getPassword().equals(data.getOldPassword())) {
            			updateOne.setPassword(passwordEncode.encode(data.getNewPassword()));            			
            		} else {
            			throw new RuntimeException("New password didn't match old password!");
            		}
            	}
            	if(data.getFileCode()!=null && data.getFileExt()!=null) {
            		File file = new File();
            		file.setFileCode(data.getFileCode());
            		file.setFileExt(data.getFileExt());
            		file.setCreatedBy(principalService.getPrincipal().getId());
            		file = fileDao.insert(file);
            		updateOne.setPhoto(file);
            	}
                updateOne.setUpdatedBy(principalService.getPrincipal().getId());
                updateOne.setIsActive(data.getIsActive());
                updateOne = userDao.update(updateOne);
                final UpdateResDto responseDb = new UpdateResDto();
                responseDb.setVer(updateOne.getVer());
                responseBe.setData(responseDb);
                responseBe.setMessage(ResponseConst.UPDATED.getResponse() + " " + ModelConst.USER.getResponse());
            } catch (Exception e) {
                responseBe.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return responseBe;
    }
	
	public DataResDto<UserDataDto> getById(final Long id) {
        final Optional<User> optional = userDao.getById(id);
        User findOne = null;
        if (optional.isPresent()) {
            findOne = optional.get();
            final UserDataDto responseDb = new UserDataDto();
            responseDb.setId(findOne.getId());
            responseDb.setEmail(findOne.getEmail());
            responseDb.setFullname(findOne.getFullname());
            responseDb.setRoleId(findOne.getRole().getId());
            responseDb.setRoleCode(findOne.getRole().getRoleCode());
            responseDb.setRoleName(findOne.getRole().getRoleName());
            if(findOne.getPhoto()!=null) {
            	responseDb.setFileId(findOne.getPhoto().getId());            	
            }
            responseDb.setVer(findOne.getVer());
            responseDb.setIsActive(findOne.getIsActive());
            final DataResDto<UserDataDto> responseBe = new DataResDto<UserDataDto>();
            responseBe.setData(responseDb);
            return responseBe;
        } else {
            throw new RuntimeException(ModelConst.USER.getResponse() + " not found!");
        }
    }
	
	 public UserListDataDto getAll() {
	        final List<UserDataDto> responseDb = new ArrayList<>();
	        final List<User> find = userDao.getAll();
	        for (int i = 0; i < find.size(); i++) {
	            final User user = find.get(i);
	            final UserDataDto result = new UserDataDto();
	            result.setId(user.getId());
	            result.setEmail(user.getEmail());
	            result.setFullname(user.getFullname());
	            result.setRoleId(user.getRole().getId());
	            result.setRoleCode(user.getRole().getRoleCode());
	            result.setRoleName(user.getRole().getRoleName());
	            if(user.getPhoto()!=null) {
	            	result.setFileId(user.getPhoto().getId());            	
	            }
	            result.setVer(user.getVer());
	            result.setIsActive(user.getIsActive());
	            responseDb.add(result);
	        }
	        final UserListDataDto responseBe = new UserListDataDto();
	        responseBe.setData(responseDb);
	        return responseBe;
	   }

}
