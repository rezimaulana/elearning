package com.lawencon.elearning.dto.submission;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SubmissionDataDto {
    
    private Long id;
    private Float score;

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

    private Long fileId;

    private Boolean isActive;
    private Integer ver;

}
