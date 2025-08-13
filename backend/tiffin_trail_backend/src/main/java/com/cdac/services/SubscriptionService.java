package com.cdac.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.SubscriptionPlanRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.Repositories.UserSubscriptionRepository;
import com.cdac.entity.SubscriptionPlan;
import com.cdac.entity.User;
import com.cdac.entity.UserSubscription;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SubscriptionService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscription subscribe(Long planId, String email) {
        SubscriptionPlan plan = subscriptionPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Subscription plan not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusDays(plan.getDurationDays()));
        subscription.setPaymentStatus("PENDING");
        subscription.setPaymentMode("OFFLINE");
        subscription.setStatus("ACTIVE");

        return userSubscriptionRepository.save(subscription);
    }

    public List<UserSubscription> getSubscriptionsByCustomer(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (user.getCustomerProfile() == null) {
//            throw new RuntimeException("User is not a customer");
//        }

        return userSubscriptionRepository.findByUser(user);
    }
    }