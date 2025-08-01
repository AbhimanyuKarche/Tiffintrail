package com.cdac.Repositories;

import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface SellerRepository extends JpaRepository<SellerProfile, Long> {
    SellerResponseDto findByUser(User user);
}
