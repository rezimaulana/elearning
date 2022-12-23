package com.lawencon.elearning.dto.activity;

import lombok.Data;

@Data
public class ActivityDataDto {
    
    private Long id;
    private String activityCode;
    private String activityType;
    private Boolean isActive;
    private Integer ver;

}
