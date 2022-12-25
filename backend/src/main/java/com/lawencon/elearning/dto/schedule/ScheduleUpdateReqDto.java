package com.lawencon.elearning.dto.schedule;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.lawencon.elearning.model.File;

import lombok.Data;

@Data
public class ScheduleUpdateReqDto {
    
    @NotNull
    private Long id;

    @NotNull
    private String startTime;
    
    @NotNull
    private String endTime;

    @NotNull
    private String materialSubject;

    @NotNull
    private String materialDescription;
    
    private List<File> file;

    @NotNull
    private Boolean isActive;

}
