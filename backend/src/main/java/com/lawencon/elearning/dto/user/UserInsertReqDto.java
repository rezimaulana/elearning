package com.lawencon.elearning.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserInsertReqDto {
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    private String fullname;

}
