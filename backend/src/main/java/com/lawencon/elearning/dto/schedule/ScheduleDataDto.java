package com.lawencon.elearning.dto.schedule;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDataDto {
    
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long materialId;
    private String materialCode;
    private String materialSubject;
    private String materialDescription;
    
    private Long activityId;
    private String activityCode;
    private String activityType;

    private Boolean isActive;
    private Integer ver;

}
