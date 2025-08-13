package com.cdac.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
@Transactional
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerRepository customerRepo;
    private final UserRepository userRepo; // ✅ ADD THIS

    

    @Override
    public CustomerProfile createProfile(User user, CustomerProfile customerProfile) {
        customerProfile.setUser(user);
        return customerRepo.save(customerProfile);
    }

    @Override
    public CustomerProfile getCustomerFromAuth(Authentication auth) {
        if (auth == null || !(auth.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("Invalid authentication");
        }

        String email = ((UserDetails) auth.getPrincipal()).getUsername();

        return userRepo.findByEmail(email)
                .flatMap(user -> customerRepo.findByUser(user))
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));
    }

    @Override
    public Optional<CustomerProfile> getByUser(User user) {
        return customerRepo.findByUser(user);
    }


}
