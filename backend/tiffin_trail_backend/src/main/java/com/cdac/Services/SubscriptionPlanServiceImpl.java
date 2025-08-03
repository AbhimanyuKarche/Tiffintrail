package com.cdac.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.SubscriptionPlanRepository;
import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dto.SubscriptionPlanRequestDto;
import com.cdac.dto.SubscriptionPlanResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.SubscriptionPlan;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionRepo;
    private final CustomerRepository customerRepo;
    private final SellerRepository sellerRepo;
    private final ModelMapper modelMapper;
    
    //add new plan
	@Override
	public SubscriptionPlanResponseDto addSubscriptionPlan(SubscriptionPlanRequestDto dto) {
		
		
		//check first customer is exist to add plan for it
	    CustomerProfile customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

	  //check seller is exist to add plan according there tiffin and & subscription plan
        SellerProfile seller = sellerRepo.findById(dto.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));

       //then convert req dto to entity to save it
        SubscriptionPlan plan = modelMapper.map(dto, SubscriptionPlan.class);
        
        // JPA/Hibernate needs the whole entity to manage relationships properly.
        plan.setCustomerProfile(customer);
        plan.setSellerProfile(seller);
        
        
        SubscriptionPlan saved = subscriptionRepo.save(plan);
        return modelMapper.map(saved, SubscriptionPlanResponseDto.class);
   	
	}
	
	////get all plans not for specific ones
	@Override
	public List<SubscriptionPlanResponseDto> getAllPlans() {
		
		return subscriptionRepo.findAll().stream()
                .map(plan -> modelMapper.map(plan, SubscriptionPlanResponseDto.class))
                .collect(Collectors.toList());
	}
	
	//get subscription-plan by plan id
	@Override
	public SubscriptionPlanResponseDto getPlanById(Long id) {
	       SubscriptionPlan plan = subscriptionRepo.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
	        return modelMapper.map(plan, SubscriptionPlanResponseDto.class);
	    
	}
	
	//for update existing plan id and updated plan details 
	@Override
	public SubscriptionPlanResponseDto updatePlan(Long id, SubscriptionPlanRequestDto dto) {
		SubscriptionPlan plan = subscriptionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        CustomerProfile customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        SellerProfile seller = sellerRepo.findById(dto.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));

        modelMapper.map(dto, plan); // update fields
        plan.setCustomerProfile(customer);
        plan.setSellerProfile(seller);

        SubscriptionPlan updated = subscriptionRepo.save(plan);
        return modelMapper.map(updated, SubscriptionPlanResponseDto.class);
    
	}
	
	//delete plan 
	@Override
	public void deletePlan(Long id) {
		SubscriptionPlan plan = subscriptionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
        subscriptionRepo.delete(plan);

	}
	
	//get all plan for specific user 
	@Override
	public List<SubscriptionPlanResponseDto> getPlansByUser(Long userId) {
	      List<SubscriptionPlan> plans = subscriptionRepo.findByCustomerProfileId(userId);
	        return plans.stream()
	                .map(plan -> modelMapper.map(plan, SubscriptionPlanResponseDto.class))
	                .collect(Collectors.toList());
	 
	}
	
	//get all plan for specific seller
	@Override
	public List<SubscriptionPlanResponseDto> getPlansByVendor(Long vendorId) {
		List<SubscriptionPlan> plans = subscriptionRepo.findBySellerProfileId(vendorId);
        return plans.stream()
                .map(plan -> modelMapper.map(plan, SubscriptionPlanResponseDto.class))
                .collect(Collectors.toList());
	}
	

}
