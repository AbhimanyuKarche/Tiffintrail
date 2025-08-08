package com.cdac.dto;



import com.cdac.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDto {
	private Long id;

    private String fullName;

    
    private String email;
    private String password;
    private Role role;

    private boolean active;
    
	
	
	
}
