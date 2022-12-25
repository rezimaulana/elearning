package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.user.UserDataDto;
import com.lawencon.elearning.dto.user.UserInsertReqDto;
import com.lawencon.elearning.dto.user.UserListDataDto;
import com.lawencon.elearning.dto.user.UserUpdateReqDto;
import com.lawencon.elearning.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
	private UserService userService;
	
    @PreAuthorize("hasAuthority('RLSAM')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final UserInsertReqDto data){
		final TransactionResDto<InsertResDto> result = userService.insertInstructor(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final UserUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = userService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@GetMapping
	public ResponseEntity<DataResDto<UserDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<UserDataDto> result = userService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSAM')")
	@GetMapping("data")
	public ResponseEntity<UserListDataDto> getAll() {
		final UserListDataDto result = userService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}