package com.lawencon.elearning.dto.comment;

import lombok.Data;

@Data
public class CommentDataDto {
    
    private Long id;
    
    private String comment;

    private Long userId;
    private String userEmail;
    private String userFullname;
    private Long fileId;

    private Boolean isActive;
    private Integer ver;

}
