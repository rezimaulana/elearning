package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.activity.ActivityDataDto;
import com.lawencon.elearning.dto.activity.ActivityInsertReqDto;
import com.lawencon.elearning.dto.activity.ActivityListDataDto;
import com.lawencon.elearning.dto.activity.ActivityUpdateReqDto;
import com.lawencon.elearning.dto.response.DataResponseDto;
import com.lawencon.elearning.dto.response.DeleteResponseDto;
import com.lawencon.elearning.dto.response.InsertResponseDto;
import com.lawencon.elearning.dto.response.TransactionResponseDto;
import com.lawencon.elearning.dto.response.UpdateResponseDto;
import com.lawencon.elearning.service.ActivityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostMapping
	public ResponseEntity<TransactionResponseDto<InsertResponseDto>> insert(@RequestBody final ActivityInsertReqDto data){
		final TransactionResponseDto<InsertResponseDto> result = activityService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<TransactionResponseDto<UpdateResponseDto>> update(@RequestBody final ActivityUpdateReqDto data){
		final TransactionResponseDto<UpdateResponseDto> result = activityService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<DataResponseDto<ActivityDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResponseDto<ActivityDataDto> result = activityService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("data")
	public ResponseEntity<ActivityListDataDto> getAll() {
		final ActivityListDataDto result = activityService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("remove")
	public ResponseEntity<DeleteResponseDto> delete(@RequestParam("id") final Long id) {
		final DeleteResponseDto result = activityService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
