package com.lawencon.elearning.dto.submission;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubmissionUpdateReqDto {
    
    @NotNull
    private Long id;

    @NotNull
    private Float score;

    private String fileCode;
    
    private String fileExt;

    @NotNull
    private Boolean isActive;

}
