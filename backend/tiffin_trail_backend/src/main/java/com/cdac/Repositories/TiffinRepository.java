package com.cdac.Repositories;


import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface TiffinRepository extends JpaRepository<Tiffin, Long> {
    List<Tiffin> findBySeller(SellerProfile seller);
    List<Tiffin> findByAvailableTrue(); // Only get available tiffins
}
