package com.cdac.dto;



import com.cdac.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequestDto {
	
	    @NotBlank(message = "Full Name is required")
	    private String fullName;
	    @NotBlank
		@Email(message = "invalid email format")
	  
	    private String email;
	    @Pattern
		(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", 
		message = "Invalid password format")
	    private String password;
	    
	    @NotNull
	    private Role role;

	    private boolean active ;

	}


		
		
	

