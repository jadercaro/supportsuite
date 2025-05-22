package com.telco.users.service;

import com.telco.users.dto.LoginRequestDTO;
import com.telco.users.dto.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO authenticate(LoginRequestDTO request);
}
