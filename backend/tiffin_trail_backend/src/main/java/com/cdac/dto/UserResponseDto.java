package com.cdac.dto;

import javax.management.relation.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDto {
	private Long id;

    private String fullName;

    
    private String email;
    private Role role;

    private boolean active;
	
	
	
}
