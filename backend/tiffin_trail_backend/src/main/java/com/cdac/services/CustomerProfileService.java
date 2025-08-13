package com.cdac.services;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;

public interface CustomerProfileService {
	 CustomerProfile createProfile(User user, CustomerProfile customerProfile);
	    CustomerProfile getCustomerFromAuth(Authentication auth);
	    Optional<CustomerProfile> getByUser(User user);
	}
