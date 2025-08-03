package com.cdac.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;
public interface SellerRepository extends JpaRepository<SellerProfile, Long> {
	Optional<SellerProfile> findByUser(User user);
}
