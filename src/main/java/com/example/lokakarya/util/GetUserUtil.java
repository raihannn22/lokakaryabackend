package com.example.lokakarya.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.UserRepo;

@Component
public class GetUserUtil {
    @Autowired
    private UserRepo userRepo;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (user == null) {
            throw new RuntimeException("Current User Couldn't be found!");
        }

        return user;
    }
}
