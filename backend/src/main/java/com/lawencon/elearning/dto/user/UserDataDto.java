package com.lawencon.elearning.dto.user;

import lombok.Data;

@Data
public class UserDataDto {
    
    private Long id;
    private String email;
    private String fullname;
    private Long roleId;
    private String roleCode;
    private String roleName;
    private Long fileId;
    private Integer ver;
    private Boolean isActive;

}
