package com.cdac.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.User;
import com.cdac.entity.UserSubscription;

public interface UserSubscriptionRepository  extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUser(User user);
}
