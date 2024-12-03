package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.ChangePassDto;
import com.example.LokaKarya.Dto.LoginDto;
import com.example.LokaKarya.Dto.LoginResponseDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Entity.User;

import java.util.Optional;

public interface AuthServ{
    LoginResponseDto login(LoginDto data);
    UserDto changePassword(ChangePassDto data);
}
