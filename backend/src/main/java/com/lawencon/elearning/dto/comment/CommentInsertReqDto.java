package com.lawencon.elearning.dto.comment;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentInsertReqDto {
    
    @NotNull
    private String comment;

    @NotNull
    private Long forumId;

}
