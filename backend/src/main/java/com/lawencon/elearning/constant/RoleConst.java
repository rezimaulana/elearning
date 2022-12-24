package com.lawencon.elearning.constant;

import lombok.Getter;

@Getter
public enum RoleConst {

    SYSTEM("RLSYS", "System"),
	SUPERADMIN("RLSAM", "Super Admin"), 
    INSTRUCTOR("RLINS", "Instructor"),
	STUDENT("RLSTD", "Student");

	private final String roleCodeEnum;
	private final String roleNameEnum;
	
	RoleConst(final String roleCodeEnum, final String roleNameEnum) {
		this.roleCodeEnum = roleCodeEnum;
		this.roleNameEnum = roleNameEnum;
		}

}
