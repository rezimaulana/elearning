package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.user.UserInsertReqDto;
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

	// @PutMapping
	// public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final ActivityUpdateReqDto data){
	// 	final TransactionResDto<UpdateResDto> result = activityService.update(data);
	// 	return new ResponseEntity<>(result, HttpStatus.OK);
	// }
	
	// @GetMapping
	// public ResponseEntity<DataResDto<ActivityDataDto>> getById(@RequestParam(required = true) final Long id){
	// 	final DataResDto<ActivityDataDto> result = activityService.getById(id);
	// 	return new ResponseEntity<>(result, HttpStatus.OK);
	// }

	// @GetMapping("data")
	// public ResponseEntity<ActivityListDataDto> getAll() {
	// 	final ActivityListDataDto result = activityService.getAll();
	// 	return new ResponseEntity<>(result, HttpStatus.OK);
	// }

}