package com.lawencon.elearning.dto.attendance;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AttendanceUpdateReqDto {
    
    @NotNull
    private Long id;

    @NotNull
    private Boolean approval;

    @NotNull
    private Boolean isActive;

}
