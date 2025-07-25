package com.cdac.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public class Order {
	@NotBlank
	private int id;
	@NotBlank
	private Date date;
	@NotBlank
	private String feedback;
	@NotBlank
	@Length(min = 10, max = 10)
	private String mobile;
	@NotBlank
	private Boolean status;
	@NotBlank
	private int quantity;

}
