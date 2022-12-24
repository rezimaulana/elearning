package com.lawencon.elearning.dto.user;

import lombok.Data;

@Data
public class UserUpdateReqDto {
    
	private Long id;
    private String fullname;
    private String oldPassword;
    private String newPassword;
    private String fileCode;
    private String fileExt;
    private Boolean isActive;

}
