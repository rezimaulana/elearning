package com.lawencon.elearning.dto.class_hdr;

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
    private Boolean isActive;
    private Integer ver;

}
