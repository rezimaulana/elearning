package com.lawencon.elearning.dto.class_hdr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClassHdrInsertReqDto {
    
    @NotNull
    @Size(min=1, max=200)
    private String classHdrSubject;
    
    @NotNull
    private String classHdrDescription;
    
    @NotNull
    private Long insId;
    
    @NotNull
    private String fileCode;
    
    @NotNull
    private String fileExt;

}
