package com.lawencon.elearning.constant;

import lombok.Getter;

@Getter
public enum ModelConst {
		
	ACTIVITY("Activity");
	
	private final String response;
	
	ModelConst(final String response) {
		this.response = response;
	}

}
