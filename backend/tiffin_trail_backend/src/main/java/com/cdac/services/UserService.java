package com.cdac.services;

import java.util.Optional;

import com.cdac.entity.User;

public interface UserService {
	 User saveUser(User user);
	    Optional<User> findByEmail(String email);

   
}
