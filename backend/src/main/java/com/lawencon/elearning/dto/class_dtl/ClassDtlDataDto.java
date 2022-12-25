package com.lawencon.elearning.dto.class_dtl;

import lombok.Data;

@Data
public class ClassDtlDataDto {
    
    private Long id;

    private Long stdId;
    private String stdEmail;
    private String stdFullname;

    private Long classHdrId;
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
