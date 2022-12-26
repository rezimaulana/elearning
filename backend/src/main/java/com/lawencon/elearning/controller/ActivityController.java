package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.DeleteResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.service.ActivityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PreAuthorize("hasAuthority('RLSAM')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final ActivityInsertReqDto data){
		final TransactionResDto<InsertResDto> result = activityService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('RLSAM')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final ActivityUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = activityService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('RLSAM')")
	@GetMapping
	public ResponseEntity<DataResDto<ActivityDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<ActivityDataDto> result = activityService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSAM')")
	@GetMapping("data")
	public ResponseEntity<ActivityListDataDto> getAll() {
		final ActivityListDataDto result = activityService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSAM')")
	@DeleteMapping
	public ResponseEntity<DeleteResDto> delete(@RequestParam("id") final Long id) {
		final DeleteResDto result = activityService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@GetMapping("code")
	public ResponseEntity<DataResDto<ActivityDataDto>> getByCode(@RequestParam(required = true) final String code){
		final DataResDto<ActivityDataDto> result = activityService.getByCode(code);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
