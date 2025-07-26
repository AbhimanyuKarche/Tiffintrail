package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.SubscriptionPlan;

public interface SubscriptionPlanDao extends JpaRepository<SubscriptionPlan, Long> {

}
