package com.lawencon.elearning.dto.login;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginReqDto {
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
}
