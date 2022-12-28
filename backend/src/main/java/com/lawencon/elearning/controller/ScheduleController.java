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
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.dto.schedule.ScheduleDataDto;
import com.lawencon.elearning.dto.schedule.ScheduleInsertReqDto;
import com.lawencon.elearning.dto.schedule.ScheduleUpdateReqDto;
import com.lawencon.elearning.service.ScheduleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("schedules")
public class ScheduleController {
    
    @Autowired
	private ScheduleService scheduleService;
	
    @PreAuthorize("hasAuthority('RLINS')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final ScheduleInsertReqDto data){
        final TransactionResDto<InsertResDto> result = scheduleService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
    
    @PreAuthorize("hasAuthority('RLINS')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final ScheduleUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = scheduleService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    @PreAuthorize("hasAnyAuthority('RLSAM', 'RLINS', 'RLSTD')")
    @GetMapping("user")
	public ResponseEntity<DataListResDto<ScheduleDataDto>> getAll(@RequestParam("activity") final Long activityId, @RequestParam("class-hdr") final Long classHdrId) {
		final DataListResDto<ScheduleDataDto> result = scheduleService.getAllByUser(activityId, classHdrId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
