package com.lawencon.elearning.dto.user;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserUpdateReqDto {
    
    private String fullname;
    
    private String oldPassword;
    private String newPassword;
    
    private String fileCode;
    private String fileExt;
    
    @NotNull
    private Boolean isActive;

}
