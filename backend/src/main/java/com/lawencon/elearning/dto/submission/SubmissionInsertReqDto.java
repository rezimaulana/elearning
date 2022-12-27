package com.lawencon.elearning.dto.submission;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubmissionInsertReqDto {
    
    @NotNull
    private Long classDtlId;

    @NotNull
    private Long scheduleId;

    @NotNull
    private String fileCode;

    @NotNull
    private String fileExt;

}
