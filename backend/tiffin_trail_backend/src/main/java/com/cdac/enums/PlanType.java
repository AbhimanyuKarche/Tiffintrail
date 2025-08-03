package com.cdac.enums;

import lombok.Getter;

@Getter
public enum PlanType {
	WEEKLY(7),MONTHLY(28);
	private final int duration;

	private PlanType(int duration) {
		this.duration = duration;
	}

	
	
}
