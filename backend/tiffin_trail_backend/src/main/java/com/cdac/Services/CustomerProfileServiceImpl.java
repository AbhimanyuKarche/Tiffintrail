package com.cdac.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
	private final CustomerRepository customerRepo;
	@Override
	public CustomerProfile createProfile(User user, CustomerProfile customerProfile) {
		customerProfile.setUser(user);
        return customerRepo.save(customerProfile);
	}

	@Override
	public Optional<CustomerProfile> getByUser(User user) {
		return customerRepo.findByUser(user);
	}

}
