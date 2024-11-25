package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.LoginDto;
import com.example.LokaKarya.Dto.LoginResponseDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.AuthServ;
import com.example.LokaKarya.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServImpl implements AuthServ {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public LoginResponseDto login(LoginDto data) {
        User user = userRepo.findByEmail(data.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        System.out.println(user.getPassword() + " dan " + data.getPassword());
        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user);

        // Konversi User ke UserDto
        UserDto userDto = UserDto.fromEntity(user);

        // Buat LoginResponseDto dan kembalikan
        return   LoginResponseDto.builder()
                .user(userDto)
                .token(token)
                .build();
    }
}
