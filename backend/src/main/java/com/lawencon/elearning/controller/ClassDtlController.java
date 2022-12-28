package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.class_dtl.ClassDtlDataDto;
import com.lawencon.elearning.dto.class_dtl.ClassDtlInsertReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.service.ClassDtlService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("class-dtl")
public class ClassDtlController {
    
    @Autowired
	private ClassDtlService classDtlService;
	
    @PreAuthorize("hasAuthority('RLSTD')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final ClassDtlInsertReqDto data){
		final TransactionResDto<InsertResDto> result = classDtlService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

    @PreAuthorize("hasAuthority('RLSTD')")
	@GetMapping
	public ResponseEntity<DataResDto<ClassDtlDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<ClassDtlDataDto> result = classDtlService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @PreAuthorize("hasAuthority('RLSTD')")
	@GetMapping("student")
	public ResponseEntity<DataListResDto<ClassDtlDataDto>> getAllByStudent() {
		final DataListResDto<ClassDtlDataDto> result = classDtlService.getAllByStudent();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
