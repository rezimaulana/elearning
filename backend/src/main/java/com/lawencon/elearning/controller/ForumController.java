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

import com.lawencon.elearning.dto.forum.ForumDataDto;
import com.lawencon.elearning.dto.forum.ForumInsertReqDto;
import com.lawencon.elearning.dto.forum.ForumUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.service.ForumService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("forums")
public class ForumController {
    
    @Autowired
    private ForumService forumService;

	@PreAuthorize("hasAuthority('RLSTD')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final ForumInsertReqDto data){
		final TransactionResDto<InsertResDto> result = forumService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('RLSTD')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final ForumUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = forumService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@GetMapping
	public ResponseEntity<DataResDto<ForumDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<ForumDataDto> result = forumService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping("instructor")
	public ResponseEntity<DataListResDto<ForumDataDto>> getAllByInstructor(@RequestParam final Long classHdrId) {
		final DataListResDto<ForumDataDto> result = forumService.getAllByInstructor(classHdrId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSTD')")
	@GetMapping("student")
	public ResponseEntity<DataListResDto<ForumDataDto>> getAllByStudent(@RequestParam final Long classDtlId) {
		final DataListResDto<ForumDataDto> result = forumService.getAllByStudent(classDtlId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
