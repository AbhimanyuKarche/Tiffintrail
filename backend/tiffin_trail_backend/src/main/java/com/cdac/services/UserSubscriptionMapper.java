package com.cdac.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cdac.dto.SubscriptionPlanDTO;
import com.cdac.dto.UserSubscriptionDTO;
import com.cdac.entity.UserSubscription;

@Service
public class UserSubscriptionMapper {

    public UserSubscriptionDTO toDTO(UserSubscription sub) {
        return new UserSubscriptionDTO(
                sub.getId(),
                sub.getStartDate(),
                sub.getEndDate(),
                sub.getStatus(),
                "PAID".equalsIgnoreCase(sub.getPaymentStatus()),  // assuming paid if status is PAID
                new SubscriptionPlanDTO(
                        sub.getPlan().getId(),
                        sub.getPlan().getName(),
                        sub.getPlan().getPrice()
                )
        );
    }

    public List<UserSubscriptionDTO> toDTOList(List<UserSubscription> subs) {
        return subs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
