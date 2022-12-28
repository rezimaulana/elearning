package com.lawencon.elearning.dto.comment;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentUpdateReqDto {
    
    @NotNull
    private Long id;

    @NotNull
    private String comment;

    @NotNull
    private Boolean isActive;

}
