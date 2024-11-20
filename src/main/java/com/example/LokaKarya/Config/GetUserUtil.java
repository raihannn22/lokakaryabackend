package com.example.LokaKarya.Config;

import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.UserRepo;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetUserUtil {
    @Autowired
    private UserRepo userRepo;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = (String) auth.getPrincipal();

        // return userOpt.get();
        String username = (String) auth.getPrincipal();
        System.out.println(username + " INIIIIIIIIIIIIII");
         User user = userRepo.findByUsername(username);
         if (user == null) {
         throw new RuntimeException("Current User Couldn't be found!");
         }

        return user;
    }
}
