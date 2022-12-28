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

import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.submission.SubmissionDataDto;
import com.lawencon.elearning.dto.submission.SubmissionInsertReqDto;
import com.lawencon.elearning.dto.submission.SubmissionUpdateReqDto;
import com.lawencon.elearning.service.SubmissionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("submissions")
public class SubmissionController {
    
    @Autowired
    private SubmissionService submissionService;

    @PreAuthorize("hasAuthority('RLSTD')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final SubmissionInsertReqDto data){
		final TransactionResDto<InsertResDto> result = submissionService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final SubmissionUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = submissionService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping
	public ResponseEntity<DataResDto<SubmissionDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<SubmissionDataDto> result = submissionService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping("instructor")
	public ResponseEntity<DataListResDto<SubmissionDataDto>> getAllByInstructor(@RequestParam final Long scheduleId, @RequestParam final Long classHdrId) {
		final DataListResDto<SubmissionDataDto> result = submissionService.getAllByInstructor(scheduleId, classHdrId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSTD')")
	@GetMapping("student")
	public ResponseEntity<DataListResDto<SubmissionDataDto>> getAllByStudent(@RequestParam final Long scheduleId, @RequestParam final Long classDtlId) {
		final DataListResDto<SubmissionDataDto> result = submissionService.getAllByStudent(scheduleId, classDtlId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

