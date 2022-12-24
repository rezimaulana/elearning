package com.lawencon.elearning.dto.login;

import lombok.Data;

@Data
public class LoginResDto {

	private Long id;
	private String roleCode;
	private String token;
	private String fullname;

}
