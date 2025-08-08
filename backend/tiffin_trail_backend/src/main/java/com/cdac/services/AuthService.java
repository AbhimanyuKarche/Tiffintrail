package com.cdac.services;

import com.cdac.dto.AuthRequest;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    String register(RegisterRequest request);
}