package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.SubscriptionPlanRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.entity.SubscriptionPlan;
import com.cdac.entity.Tiffin;
import com.cdac.entity.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seller/plans")
@RequiredArgsConstructor
public class SellerPlanController {

    private final SubscriptionPlanRepository planRepo;
    private final TiffinRepository tiffinRepo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<SubscriptionPlan> createPlan(
            @RequestBody SubscriptionPlan plan,
            Authentication authentication) {

        // Get logged-in seller from DB by email (from token)
        String email = authentication.getName();
        User seller = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        // Fetch the full Tiffin from DB using ID from the incoming plan
        Tiffin tiffin = tiffinRepo.findById(plan.getTiffin().getId())
                .orElseThrow(() -> new RuntimeException("Tiffin not found"));

        // Check that the tiffin belongs to this seller
        if (!tiffin.getSeller().getId().equals(seller.getId())) {
            throw new RuntimeException("You can only create plans for your own tiffins");
        }

        // Assign fetched managed entities to the plan before saving
        plan.setSeller(seller);
        plan.setTiffin(tiffin);
        plan.setActive(true);  // optionally set active true if needed

        SubscriptionPlan savedPlan = planRepo.save(plan);
        return ResponseEntity.ok(savedPlan);
    }
    @GetMapping
    public ResponseEntity<List<SubscriptionPlan>> getMyPlans(Authentication authentication) {
        String email = authentication.getName();

        User seller = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        List<SubscriptionPlan> plans = planRepo.findBySellerId(seller.getId());
        return ResponseEntity.ok(plans);
    }



}
