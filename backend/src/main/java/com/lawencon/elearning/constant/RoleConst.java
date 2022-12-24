package com.lawencon.elearning.constant;

import lombok.Getter;

@Getter
public enum RoleConst {

    SYSTEM("RLSYS", "System"),
	SUPER_ADMIN("RLSAM", "Super Admin"), 
    INSTRUCTOR("RLINS", "Instructor"),
	STUDENT("RLSTD", "Student");

	private final String roleCode;
	private final String roleName;
	
	RoleConst(final String roleCode, final String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
		}

}
