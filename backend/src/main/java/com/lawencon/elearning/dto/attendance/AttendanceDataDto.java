package com.lawencon.elearning.dto.attendance;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AttendanceDataDto {
    
    private Long id;
    private Boolean approval;

    private Long classDtlId;
    private Long stdId;
    private String stdEmail;
    private String stdFullname;
    private Long classHdrId;
    private String classHdrCode;
    private String classHdrSubject;
    private Long insId;
    private String insEmail;
    private String insFullname;

    private Long shceduleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long materialId;
    private String materialCode;
    private String materialSubject;
    private Long activityId;
    private String activityCode;
    private String activityType;

    private Boolean isActive;
    private Integer ver;


}
