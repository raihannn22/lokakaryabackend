package com.example.LokaKarya.Services.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.LokaKarya.Dto.User.UserReqUpdateDto;
import com.example.LokaKarya.Dto.User.UserResetPassDto;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;
import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.AppUserRole;
import com.example.LokaKarya.Entity.Division;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.AppRoleRepo;
import com.example.LokaKarya.Repository.AppUserRoleRepo;
import com.example.LokaKarya.Repository.DivisionRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.UserServ;

import jakarta.transaction.Transactional;

@Service
public class UserServImpl implements UserServ {
    private final Logger Log = LoggerFactory.getLogger(UserServImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AppRoleRepo appRoleRepo;

    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    private DivisionRepo divisionRepo;

    @Autowired
    private GetUserUtil getUserUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;



    @Override
    public List<UserDto> getAllUsers() {
        Log.info("Start getAllUsers in UserServImpl");
//       UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
//       System.out.println(currentUserEntity + "akunoin");

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
    @Transactional
    public UserDto createUser(UserReqDto userDto) {
        Log.info("Start createUser in UserServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        User user = UserReqDto.toEntity(userDto, currentUserId, new java.util.Date(), null, null);


        // Validasi Username Unik
        if (userRepo.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }
        // Validasi Email Unik
        if (userRepo.existsByEmail(userDto.getEmailAddress())) {
            throw new IllegalArgumentException("Email already exists: " + userDto.getEmailAddress());
        }


        // Validasi apakah ada id divisi
        if (userDto.getDivision() !=null) {
            Optional<Division> idDivision =  divisionRepo.findById(userDto.getDivision());
            if (idDivision.isEmpty()) {
                throw new RuntimeException("Division not found");
            }else {
                user.setDivision(idDivision.get());
            }
        }
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user = userRepo.save(user);

        if (userDto.getAppRole() !=null) {

            for (UUID roleId: userDto.getAppRole()) {
                System.out.println(roleId + "ini id role!123213!");

                Optional<AppRole> idRole =  appRoleRepo.findById(roleId);
                if (idRole.isEmpty()) {
                    throw new RuntimeException("Role not found");
                }else {
                    System.out.println("INI ROLE NYA: " + idRole.get());
                    AppUserRole appUserRole = new AppUserRole();
                    System.out.println(idRole + "ini id role!!!");
                    appUserRole.setAppRole(idRole.get());
                    appUserRole.setUser(user);
                    appUserRoleRepo.save(appUserRole);
                }
            };
        }
        Log.info("End createUser in UserServImpl");
        return UserDto.fromEntity(user);
    }

    @Override
    @Transactional
    public UserDto updateUser (UUID id, UserReqUpdateDto userDto) {
        Log.info("Start updateUser in UserServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        User findUser  = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));

        updateUserFields2(findUser, userDto);
        findUser.setUpdatedBy(currentUserId);
        findUser.setUpdatedAt(new java.util.Date());



        appUserRoleRepo.deleteByUserId(id);
        if (userDto.getDivision() != null) {
            Division division = divisionRepo.findById(userDto.getDivision()).orElseThrow(() -> new RuntimeException("Division not found"));
            findUser.setDivision(division);
        }


        findUser = userRepo.save(findUser);

        if (userDto.getAppRole() !=null) {
            List<AppUserRole> appUserRoles = appUserRoleRepo.findByUserId(id);

            appUserRoles.forEach(appUserRole -> appUserRoleRepo.delete(appUserRole));
            for (UUID roleId: userDto.getAppRole()) {
                System.out.println(roleId + "ini id role!123213!");

                Optional<AppRole> idRole =  appRoleRepo.findById(roleId);
                if (idRole.isEmpty()) {
                    throw new RuntimeException("Role not found");
                }else {
                    System.out.println("INI ROLE NYA: " + idRole.get());
                    AppUserRole appUserRole = new AppUserRole();
                    System.out.println(idRole + "ini id role!!!");
                    appUserRole.setAppRole(idRole.get());
                    appUserRole.setUser(findUser);
                    appUserRoleRepo.save(appUserRole);
                }
            };
        }
        Log.info("End updateUser in UserServImpl");
        return UserDto.fromEntity(findUser);
    }

    @Override
    @Transactional
    public Boolean deleteUser(UUID id) {
//        Log.info("Start deleteUser in UserServImpl");
        User findUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        appUserRoleRepo.deleteByUserId(id);
        userRepo.delete(findUser);
//        Log.info("End deleteUser in UserServImpl");
        return true;
    }

    @Override
    public UserDto resetPassword(UUID id, UserResetPassDto userDto) {
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        User findUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        findUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        findUser.setUpdatedAt(new java.util.Date());
        findUser.setUpdatedBy(currentUserId);
//
        findUser = userRepo.save(findUser);
        return UserDto.fromEntity(findUser);
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
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
    }

    private void updateUserFields2(User existingUser, UserReqUpdateDto userDto) {
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
    }

}

