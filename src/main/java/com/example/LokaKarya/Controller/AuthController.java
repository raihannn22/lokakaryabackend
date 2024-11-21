package com.example.LokaKarya.Controller;

import com.example.LokaKarya.Dto.LoginDto;
import com.example.LokaKarya.Dto.LoginResponseDto;
import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Services.AuthServ;
import com.example.LokaKarya.Services.UserServ;
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
}
