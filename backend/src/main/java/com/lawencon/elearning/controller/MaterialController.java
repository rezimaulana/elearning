package com.lawencon.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.dto.material.MaterialDataDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.service.MaterialService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("materials")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;

    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
	@GetMapping
	public ResponseEntity<DataResDto<MaterialDataDto>> getById(@RequestParam(required = true) final Long scheduleId,
        @RequestParam(required = false) final Long classDtlId
    ){
		final DataResDto<MaterialDataDto> result = materialService.getById(scheduleId, classDtlId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
