package com.lawencon.elearning.dto.class_dtl;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClassDtlInsertReqDto {
    
    @NotNull
    private Long classHdrId;

}
