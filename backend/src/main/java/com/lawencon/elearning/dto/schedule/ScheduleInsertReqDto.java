package com.lawencon.elearning.dto.schedule;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.lawencon.elearning.model.File;

import lombok.Data;

@Data
public class ScheduleInsertReqDto {
    
    @NotNull
    private String startTime;
    
    @NotNull
    private String endTime;

    @NotNull
    private String materialSubject;

    @NotNull
    private String materialDescription;

    @NotNull
    private Long classHdrId;

    @NotNull
    private Long activityId;
    
    @NotNull
    private List<File> file;

}
