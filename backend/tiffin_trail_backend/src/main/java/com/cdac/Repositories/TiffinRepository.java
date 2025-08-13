package com.cdac.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;
public interface TiffinRepository extends JpaRepository<Tiffin, Long> {
    List<Tiffin> findBySeller(SellerProfile seller);
    List<Tiffin> findBySellerId(Long sellerId);
    List<Tiffin> findByAvailableTrue(); // Only get available tiffins
}
