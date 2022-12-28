package com.lawencon.elearning.dto.forum;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ForumDataDto {
    
    private Long id;
    private String title;
    private String content;

    private Long classDtlId;
    private Long stdId;
    private String stdEmail;
    private String stdFullname;
    private Long classHdrId;
    private Long insId;
    private String insEmail;
    private String insFullname;

    private LocalDateTime createdAt;
    private Boolean isActive;
    private Long ver;

}
