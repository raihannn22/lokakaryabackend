package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;
import com.example.LokaKarya.Entity.*;
import com.example.LokaKarya.Repository.*;
import com.example.LokaKarya.Services.UserServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class


UserServImpl implements UserServ {

    private final Logger Log = LoggerFactory.getLogger(UserServImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppRoleRepo appRoleRepo;

    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    private DivisionRepo divisionRepo;



    @Override
    public List<UserDto> getAllUsers() {
        Log.info("Start getAllUsers in UserServImpl");
        List<User> response = userRepo.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : response) {
            UserDto userDto = UserDto.fromEntity(user);
            userList.add(userDto);
        }
        Log.info("End getAllUsers in UserServImpl");
        return userList;
    }

    @Override
    public UserDto getUserById(UUID id) {
//        Log.info("Start getUserById in UserServImpl");
        Optional<User> user = userRepo.findById(id);
//        System.out.println("coba ini "+user);
//        Log.info("End getUserById in UserServImpl");
        return user.map(UserDto::fromEntity).orElse(null);
    }

    @Override
    public UserDto createUser(UserReqDto userDto) {
//        Log.info("Start createUser in UserServImpl");
//        idRole = appRoleRepo.findById()
        System.out.println(userDto.getAppRole());
        if (userDto.getAppRole() !=null) {
            for (UUID roleId: userDto.getAppRole()) {
               Optional<AppRole> idRole =  appRoleRepo.findById(roleId);
               if (idRole.isEmpty()) {
                   throw new RuntimeException("Role not found");
               }else {
                   AppUserRole appUserRole = new AppUserRole();
                   appUserRole.setAppRole(idRole.get());
                   appUserRole.setUser(UserReqDto.toEntity(userDto));
                   appUserRoleRepo.save(appUserRole);
               }
            };
        }

        User user = UserReqDto.toEntity(userDto);

        if (userDto.getDivision() !=null) {
            Optional<Division> idDivision =  divisionRepo.findById(userDto.getDivision());
            if (idDivision.isEmpty()) {
                throw new RuntimeException("Division not found");
            }else {
                user.setDivision(idDivision.get());
            }
        }


        userRepo.save(user);
//        Log.info("End createUser in UserServImpl");
        return UserDto.fromEntity(user);
    }

    @Override
    public List<AppUserRoleReqDto> updateUser (UUID id, UserReqDto userDto) {
        Log.info("Start updateUser in UserServImpl");
        List<AppUserRole> appRole = appUserRoleRepo.findByUserId(id);
        User findUser  = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        List<AppUserRoleReqDto> appUserRoles = new ArrayList<>();
        for (AppUserRole userRole : appRole) {
            appUserRoleRepo.delete(userRole);
            }
        if (userDto.getDivision() != null) {
            Division division = divisionRepo.findById(userDto.getDivision()).orElseThrow(() -> new RuntimeException("Division not found"));
            findUser.setDivision(division);
        }

        for (UUID roleId: userDto.getAppRole()) {
            AppRole role = appRoleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            AppUserRole appUserRole = new AppUserRole();
            appUserRole.setAppRole(role);
            appUserRole.setUser(findUser);
            appUserRoleRepo.save(appUserRole);
            appUserRoles.add(AppUserRoleReqDto.fromEntity(appUserRole));
        }

        return appUserRoles;
//        userDto.setAppRole(appUserRoles);
//
//        if (userDto.getDivision() !=null) {
//            Optional<Division> idDivision =  divisionRepo.findById(userDto.getDivision());
//            if (idDivision.isEmpty()) {
//                throw new RuntimeException("Division not found");
//            }else {
//                findUser.setDivision(idDivision.get());
//            }
//        }
//        // Update fields based on userDto, falling back to findUser  if userDto field is null
//        updateUserFields(findUser , userDto);
//
//
//        userRepo.save(findUser);
////        Log.info("End updateUser in UserServImpl");
//        return UserDto.fromEntity(findUser);
    }

    @Override
    public Boolean deleteUser(UUID id) {
//        Log.info("Start deleteUser in UserServImpl");
        User findUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        userRepo.delete(findUser);
//        Log.info("End deleteUser in UserServImpl");
        return true;
    }

    private void updateUserFields(User existingUser, UserReqDto userDto) {
        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getFullName() != null) {
            existingUser.setFullName(userDto.getFullName());
        }
        if (userDto.getPosition() != null) {
            existingUser.setPosition(userDto.getPosition());
        }
        if (userDto.getEmailAddress() != null) {
            existingUser.setEmail(userDto.getEmailAddress());
        }
        if (userDto.getEmployeeStatus() != null) {
            existingUser.setEmployeeStatus(userDto.getEmployeeStatus());
        }
        if (userDto.getJoinDate() != null) {
            existingUser.setJoinDate(Date.valueOf(userDto.getJoinDate().toLocalDate()));
        }
        if (userDto.getEnabled() != null) {
            existingUser.setEnabled(userDto.getEnabled());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }

    }
}
