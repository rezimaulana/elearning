package com.lawencon.elearning.dto.activity;

import lombok.Data;

@Data
public class ActivityUpdateReqDto {
    
    private Long id;
    private String activityType;
    private Boolean isActive;
    private Integer ver;

}
