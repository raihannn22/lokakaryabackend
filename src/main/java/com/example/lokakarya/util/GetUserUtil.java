package com.example.lokakarya.util;

import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetUserUtil {
    @Autowired
    private UserRepo userRepo;

    public User getCurrentUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        // String username = (String) auth.getPrincipal();
//
//        // return userOpt.get();
//        String username = (String) auth.getPrincipal();
//        System.out.println(username + " INIIIIIIIIIIIIII");
//         User user = userRepo.findByUsername(username);
//         if (user == null) {
//         throw new RuntimeException("Current User Couldn't be found!");
//         }
//
//        return user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Mengambil principal sebagai objek User (bukan String)
        User user = (User) auth.getPrincipal();  // Casting principal menjadi User
        if (user == null) {
            throw new RuntimeException("Current User Couldn't be found!");
        }

        return user;
    }
}
