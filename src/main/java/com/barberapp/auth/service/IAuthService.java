package com.barberapp.auth.service;

import com.barberapp.auth.dto.Request.CreateUserDto;
import com.barberapp.auth.dto.Request.LoginUserDto;
import jakarta.ws.rs.core.Response;


public interface IAuthService {
    Response signup(CreateUserDto user);
    Response login(LoginUserDto user);
}
