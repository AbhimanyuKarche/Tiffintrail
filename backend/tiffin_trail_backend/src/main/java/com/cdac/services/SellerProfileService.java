package com.cdac.services;

import java.util.Optional;

import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

public interface SellerProfileService {
    SellerProfile createProfile(User user, SellerProfile profile);
    Optional<SellerProfile> getByUser(User user);
	}
