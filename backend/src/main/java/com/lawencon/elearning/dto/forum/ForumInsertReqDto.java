package com.lawencon.elearning.dto.forum;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ForumInsertReqDto {
    
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long classDtlId;

}
