package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.user.UserInsertReqDto;
import com.lawencon.elearning.service.UserService;

@RestController
@RequestMapping("register")
public class RegisterController {
    
    @Autowired
	private UserService userService;
    
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> register(@RequestBody final UserInsertReqDto data){
		final TransactionResDto<InsertResDto> result = userService.insertStudent(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
