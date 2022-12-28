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

import com.lawencon.elearning.dto.class_hdr.ClassHdrDataDto;
import com.lawencon.elearning.dto.class_hdr.ClassHdrInsertReqDto;
import com.lawencon.elearning.dto.class_hdr.ClassHdrUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.service.ClassHdrService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("class-hdr")
public class ClassHdrController {
    
    @Autowired
	private ClassHdrService classHdrService;
	
    @PreAuthorize("hasAuthority('RLSAM')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final ClassHdrInsertReqDto data){
		final TransactionResDto<InsertResDto> result = classHdrService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

    @PreAuthorize("hasAuthority('RLSAM')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final ClassHdrUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = classHdrService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    @PreAuthorize("hasAuthority('RLINS')")
	@GetMapping
	public ResponseEntity<DataResDto<ClassHdrDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<ClassHdrDataDto> result = classHdrService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@GetMapping("data")
	public ResponseEntity<DataListResDto<ClassHdrDataDto>> getAll() {
		final DataListResDto<ClassHdrDataDto> result = classHdrService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping("instructor")
	public ResponseEntity<DataListResDto<ClassHdrDataDto>> getAllByInstructor() {
		final DataListResDto<ClassHdrDataDto> result = classHdrService.getAllByInstructor();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

