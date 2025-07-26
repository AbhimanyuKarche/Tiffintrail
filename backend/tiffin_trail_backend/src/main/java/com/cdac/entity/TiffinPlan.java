package com.cdac.entity;

import java.util.List;

import com.cdac.enums.FoodType;
import com.cdac.enums.TiffinType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "subscriptionPlans")

@Entity
@Table(name = "tiffin_plan")
public class TiffinPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	@NotNull
	private Long vendorId;
	
	@Enumerated(EnumType.STRING)
	@NotBlank
	private FoodType foodType;
	
	@Enumerated(EnumType.STRING)
	@NotBlank
	private TiffinType planType;
	
	
	@NotBlank
	private String description ;
	
	@NotNull
	private double foodPrice;
	@ManyToMany(mappedBy = "tiffinPlans")
	private List<SubscriptionPlan> subscriptionPlans;
	public TiffinPlan( Long planId,  Long vendorId,   TiffinType planType,
			 String description,  FoodType foodType,  double foodPrice) {
		super();
		this.planId = planId;
		this.vendorId = vendorId;
		this.planType = planType;
		this.description = description;
		this.foodType = foodType;
		this.foodPrice = foodPrice;
	}
	
	
	
	
}
