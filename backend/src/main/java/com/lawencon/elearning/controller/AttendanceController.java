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

import com.lawencon.elearning.dto.attendance.AttendanceDataDto;
import com.lawencon.elearning.dto.attendance.AttendanceInsertReqDto;
import com.lawencon.elearning.dto.attendance.AttendanceUpdateReqDto;
import com.lawencon.elearning.dto.response.DataListResDto;
import com.lawencon.elearning.dto.response.DataResDto;
import com.lawencon.elearning.dto.response.InsertResDto;
import com.lawencon.elearning.dto.response.TransactionResDto;
import com.lawencon.elearning.dto.response.UpdateResDto;
import com.lawencon.elearning.service.AttendanceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("attendances")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;

    @PreAuthorize("hasAuthority('RLSTD')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody final AttendanceInsertReqDto data){
		final TransactionResDto<InsertResDto> result = attendanceService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@PutMapping
	public ResponseEntity<TransactionResDto<UpdateResDto>> update(@RequestBody final AttendanceUpdateReqDto data){
		final TransactionResDto<UpdateResDto> result = attendanceService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping
	public ResponseEntity<DataResDto<AttendanceDataDto>> getById(@RequestParam(required = true) final Long id){
		final DataResDto<AttendanceDataDto> result = attendanceService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLINS')")
	@GetMapping("instructor")
	public ResponseEntity<DataListResDto<AttendanceDataDto>> getAllByInstructor(@RequestParam final Long scheduleId, @RequestParam final Long classHdrId) {
		final DataListResDto<AttendanceDataDto> result = attendanceService.getAllByInstructor(scheduleId, classHdrId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('RLSTD')")
	@GetMapping("student")
	public ResponseEntity<DataListResDto<AttendanceDataDto>> getAllByStudent(@RequestParam final Long scheduleId, @RequestParam final Long classDtlId) {
		final DataListResDto<AttendanceDataDto> result = attendanceService.getAllByStudent(scheduleId, classDtlId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
