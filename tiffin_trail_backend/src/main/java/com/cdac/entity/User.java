package com.cdac.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long userId;
	@NotBlank
	private String first_Name;
	@NotBlank
	private String last_Name;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String phoneNo;
	
	@NotNull
	private LocalDate registerDate;
	@Column(name="user_role")
	@Enumerated(EnumType.STRING)
	@NotBlank
	private Role role;
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<DeliveryAddress> deliveryAddresses=new ArrayList<>();
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders=new ArrayList<>();
	@ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
	private List<SubscriptionPlan> subscriptionPlans=new ArrayList<>();
	
	public User(Long id,
			 String first_Name,  String last_Name, String email,
			 String phoneNo,  LocalDate registerDate, Role role) {
		this.userId = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.registerDate = registerDate;
		this.role = role;
	}
	
	
	
	

	
}
