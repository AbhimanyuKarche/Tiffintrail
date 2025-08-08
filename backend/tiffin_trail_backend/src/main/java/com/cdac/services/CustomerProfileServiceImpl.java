package com.cdac.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
@Transactional
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
