package com.lawencon.elearning.constant;

import lombok.Getter;

@Getter
public enum ModelConst {
		
	ACTIVITY("Activity"),
	USER("User"),
	CLASS_HDR("Class Header"),
	CLASS_DTL("Class Detail"),
	SCHEDULE("Schedule");
	
	private final String response;
	
	ModelConst(final String response) {
		this.response = response;
	}

}
