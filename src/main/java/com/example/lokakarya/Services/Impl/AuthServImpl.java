package com.example.lokakarya.Services.Impl;
import com.example.lokakarya.Dto.Auth.ChangePassDto;
import com.example.lokakarya.Dto.Auth.LoginDto;
import com.example.lokakarya.Dto.Auth.LoginResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.AuthServ;
import com.example.lokakarya.util.GetUserUtil;
import com.example.lokakarya.util.JwtUtil;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServImpl implements AuthServ {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private GetUserUtil getUserUtil;

    private static final Logger Log = LoggerFactory.getLogger(AuthServImpl.class);

    @Override
    public LoginResponseDto login(LoginDto data) {
        Log.info("Start login in AuthServImpl");
        User user = userRepo.findByEmailOrUsername(data.getEmail(), data.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        System.out.println(user.getPassword() + " dan " + data.getPassword());
        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if (user.getEnabled() == 0) {
            throw new RuntimeException("User is disabled");
        }
        String token = jwtUtil.generateToken(user);
        UserDto userDto = UserDto.fromEntity(user);
        Log.info("End login in AuthServImpl");
        return   LoginResponseDto.builder()
                .user(userDto)
                .token(token)
                .build();
    }

    public UserDto changePassword(ChangePassDto data){
        Log.info("Start changePassword in AuthServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        Optional<User> user = userRepo.findById(currentUser);

        if (!passwordEncoder.matches(data.getCurrentPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password");
        };

        if (!data.getNewPassword().equals(data.getConfirmPassword())) {
            throw new RuntimeException("Password not match");
        };

        user.get().setPassword(passwordEncoder.encode(data.getNewPassword()));
        user.get().setUpdatedAt(new java.util.Date());
        user.get().setUpdatedBy(currentUser);
        user.get().setHasChangePassword(true);

        userRepo.save(user.get());
        Log.info("End changePassword in AuthServImpl");
        return UserDto.fromEntity(user.get());
    }
}