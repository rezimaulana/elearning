package com.lawencon.elearning.dto.activity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ActivityUpdateReqDto {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size(min=1, max=20)
    private String activityType;
    
    @NotNull
    private Boolean isActive;

}
