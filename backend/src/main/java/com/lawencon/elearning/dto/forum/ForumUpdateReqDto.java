package com.lawencon.elearning.dto.forum;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ForumUpdateReqDto {
    
    @NotNull
    private Long id;

    @NotNull
    private String title;
    
    @NotNull
    private String content;

    @NotNull
    public Boolean isActive;

}
