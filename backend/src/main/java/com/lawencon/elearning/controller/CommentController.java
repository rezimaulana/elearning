package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.comment.CommentInsertReqDto;
import com.lawencon.elearning.dto.comment.CommentUpdateReqDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.service.CommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final CommentInsertReqDto data){
		final TransactionResDto<InsertResDto> result = commentService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final CommentUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = commentService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

