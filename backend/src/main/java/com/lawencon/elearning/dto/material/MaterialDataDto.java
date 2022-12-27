package com.lawencon.elearning.dto.material;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class MaterialDataDto {
    
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

    private List<Long> file;

    private Boolean isActive;
    private Integer ver;

}
