package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.SubscriptionPlanRepository;
import com.cdac.dto.SubscriptionPlanDTO;
import com.cdac.dto.UserSubscriptionDTO;
import com.cdac.entity.UserSubscription;
import com.cdac.services.SubscriptionService;
import com.cdac.services.UserSubscriptionMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserSubscriptionMapper mapper;
    private final SubscriptionPlanRepository planRepo;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @GetMapping
    public ResponseEntity<List<UserSubscriptionDTO>> getMySubscriptions(Authentication authentication) {
        String username = authentication.getName();
        var subscriptions = subscriptionService.getSubscriptionsByCustomer(username);
        return ResponseEntity.ok(mapper.toDTOList(subscriptions));
    }

    @GetMapping("/plans")
    public List<SubscriptionPlanDTO> getPlans() {
        return subscriptionPlanRepository.findAll()
                .stream()
                .map(plan -> new SubscriptionPlanDTO(
                        plan.getId(),
                        plan.getName(),
                        plan.getPrice()
                ))
                .toList();
    }

//    @GetMapping("/plans")
//    public ResponseEntity<List<SubscriptionPlan>> getAllPlans() {
//        return ResponseEntity.ok(planRepo.findAll());
////        return ResponseEntity.ok(planRepo.findByActiveTrue()); // You can also return DTOs
//    }
    @PostMapping("/subscribe")
    public ResponseEntity<UserSubscriptionDTO> subscribeToPlan(
            @RequestParam Long planId,
            Authentication authentication) {

        String email = authentication.getName();
        UserSubscription subscription = subscriptionService.subscribe(planId, email);
        UserSubscriptionDTO dto = mapper.toDTO(subscription);
        return ResponseEntity.ok(dto);
    }
    @Data
    public static class SubscribeRequest {
        private Long planId;
    }

}


