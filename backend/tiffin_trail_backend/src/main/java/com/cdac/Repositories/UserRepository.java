package com.cdac.Repositories;

import com.cdac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
} 