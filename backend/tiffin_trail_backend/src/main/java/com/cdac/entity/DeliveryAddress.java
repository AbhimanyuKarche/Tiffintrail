package com.cdac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;     // ....changes custId to addressId
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
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	public DeliveryAddress( Long addressId, String line1, String line2,
			 String pincode,  String city,  String state) {
		super();
		this.addressId = addressId;
		this.line1 = line1;
		this.line2 = line2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
	}
	
	
	

}
