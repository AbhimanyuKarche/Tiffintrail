package com.cdac.dto;

import com.cdac.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

	   
public class SellerRequestDto {
	
	    @NotNull(message = "User ID is required")
	    private Long userId;

	    @NotBlank(message = "Business name is required")
	    private String businessName;
	    
	    @NotBlank(message = "Description is required")
	    @Size(max = 500, message = "Description should not exceed 500 characters")
		private String description;
	    
	    @NotBlank(message = "Address  is required")
		private String address;
	    
	    @NotBlank(message = "City is required")
	    private String city;
	    
	    @NotBlank(message = "pincode is required")
	    private String pincode;
	    
	    @NotBlank(message = "phone is required")

	    private String phone;

		   
}
