package com.lawencon.elearning.dto.attendance;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AttendanceInsertReqDto {

    @NotNull
    private Long classDtlId;

    @NotNull
    private Long scheduleId;

}
