package com.cdac.Services;

import java.util.Optional;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;

public interface CustomerProfileService {
	CustomerProfile createProfile(User user, CustomerProfile customerProfile);
    Optional<CustomerProfile> getByUser(User user);
}
