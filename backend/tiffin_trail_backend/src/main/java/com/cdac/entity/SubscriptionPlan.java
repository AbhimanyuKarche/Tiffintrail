package com.cdac.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cdac.enums.PlanType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user", "tiffinPlans"})

@Entity
@Table(name = "subscription_plan")

public class SubscriptionPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long subscriptionId;
	@NotNull
	private Long custId;
	@Enumerated(EnumType.STRING)
	@NotNull
	private PlanType planType;
	@PastOrPresent
	@NotNull
	private LocalDate startDate;
	@Future
	@NotNull
	private LocalDate endDate;
	@NotBlank
	private String status;
	@NotBlank
	private String description;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@ManyToMany
	@JoinTable(
		    name = "subscription_tiffin",
		    joinColumns = @JoinColumn(name = "subscription_id"),
		    inverseJoinColumns = @JoinColumn(name = "plan_id")
		)
	private List<TiffinPlan> tiffinPlans=new ArrayList<>();
	public SubscriptionPlan( Long subscriptionId,  Long custId,  PlanType planType,
			   LocalDate startDate,  LocalDate endDate,  String status,
			 String description) {
		super();
		this.subscriptionId = subscriptionId;
		this.custId = custId;
		this.planType = planType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.description = description;
	}
	
	

	
	

	
	
}
