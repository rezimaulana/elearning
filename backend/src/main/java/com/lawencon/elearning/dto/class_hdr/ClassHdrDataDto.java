package com.lawencon.elearning.dto.class_hdr;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClassHdrDataDto {
    
    private Long id;
    private String classHdrCode;
    private String classHdrSubject;
    private String classHdrDescription;
    private Long insId;
    private String insEmail;
    private String insFullname;
    private Long fileId;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Integer ver;

}
