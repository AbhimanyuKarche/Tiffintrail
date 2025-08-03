package com.cdac.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Services.SubscriptionPlanService;
import com.cdac.dto.SubscriptionPlanRequestDto;
import com.cdac.dto.SubscriptionPlanResponseDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subscription-plans")
@AllArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    //Create new plan
    @PostMapping
    public ResponseEntity<SubscriptionPlanResponseDto> createPlan(@RequestBody SubscriptionPlanRequestDto dto) {
        SubscriptionPlanResponseDto created = subscriptionPlanService.addSubscriptionPlan(dto);
        return ResponseEntity.ok(created);
    }

    // Get all plans
    @GetMapping
    public ResponseEntity<List<SubscriptionPlanResponseDto>> getAllPlans() {
        List<SubscriptionPlanResponseDto> plans = subscriptionPlanService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    //Get plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPlanResponseDto> getPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionPlanService.getPlanById(id));
    }

    //update plan
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody SubscriptionPlanRequestDto dto) {
        return ResponseEntity.ok(subscriptionPlanService.updatePlan(id, dto));
    }

    //delete plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        subscriptionPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

    //get all plans by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SubscriptionPlanResponseDto>> getPlansByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(subscriptionPlanService.getPlansByUser(customerId));
    }

    //get all plans by vendor ID
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<SubscriptionPlanResponseDto>> getPlansByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(subscriptionPlanService.getPlansByVendor(vendorId));
    }
}

