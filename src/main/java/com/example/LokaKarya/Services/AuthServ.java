package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.LoginDto;
import com.example.LokaKarya.Entity.User;

import java.util.Optional;

public interface AuthServ{
    String login(LoginDto data);
}