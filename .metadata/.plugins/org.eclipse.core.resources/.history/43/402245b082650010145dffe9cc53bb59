package com.cdac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "deliveryaddress")
public class DeliveryAddress {
	@NotBlank
	private int cust_Id;
	@NotBlank
	private String line1;
	@NotBlank
	private String line2;
	@NotBlank
	private String pincode;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	public DeliveryAddress(int cust_Id, String line1, String line2,
			 String pincode,  String city,  String state) {
		super();
		this.cust_Id = cust_Id;
		this.line1 = line1;
		this.line2 = line2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
	}
	
	
	

}
