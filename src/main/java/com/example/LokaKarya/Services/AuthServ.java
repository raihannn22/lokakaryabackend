package com.example.lokakarya.Services;

import java.util.Optional;

import com.example.lokakarya.Dto.Auth.ChangePassDto;
import com.example.lokakarya.Dto.Auth.LoginDto;
import com.example.lokakarya.Dto.Auth.LoginResponseDto;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Entity.User;

public interface AuthServ{
    LoginResponseDto login(LoginDto data);
    UserDto changePassword(ChangePassDto data);
}
