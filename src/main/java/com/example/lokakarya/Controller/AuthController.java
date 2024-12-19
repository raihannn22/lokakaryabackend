package com.example.lokakarya.Controller;

import com.example.lokakarya.Dto.Auth.ChangePassDto;
import com.example.lokakarya.Dto.Auth.LoginDto;
import com.example.lokakarya.Dto.Auth.LoginResponseDto;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Services.AuthServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class AuthController {

    private final Logger Log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthServ authServ;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto data) {
        return ResponseEntity.ok(authServ.login(data));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<UserDto> changePassword(@RequestBody ChangePassDto data) {
        return ResponseEntity.ok(authServ.changePassword(data));
    }
}
