package com.cdac.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.SellerRepository;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class SellerProfileServiceImpl implements SellerProfileService {

    private final SellerRepository sellerRepo;

    

    @Override
    public SellerProfile createProfile(User user, SellerProfile profile) {
        profile.setUser(user);
        return sellerRepo.save(profile);
    }

    @Override
    public Optional<SellerProfile> getByUser(User user) {
        return sellerRepo.findByUser(user);
    }


}