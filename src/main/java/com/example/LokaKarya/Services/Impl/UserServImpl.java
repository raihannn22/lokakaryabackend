package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.UserRepo;
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

    @Override
    public List<UserDto> getAllUsers() {
//        Log.info("Start getAllUsers in UserServImpl");
        List<User> response = userRepo.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : response) {
            UserDto userDto = UserDto.fromEntity(user);
            userList.add(userDto);
        }
//        Log.info("End getAllUsers in UserServImpl");
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

        User user = UserReqDto.toEntity(userDto);

        userRepo.save(user);
//        Log.info("End createUser in UserServImpl");
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto updateUser (UUID id, UserReqDto userDto) {
//        Log.info("Start updateUser in UserServImpl");
        User findUser  = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));

        // Update fields based on userDto, falling back to findUser  if userDto field is null
        updateUserFields(findUser , userDto);

        userRepo.save(findUser);
//        Log.info("End updateUser in UserServImpl");
        return UserDto.fromEntity(findUser);
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
        if (userDto.getRoleId() != null) {
            existingUser.setRoleId(userDto.getRoleId());
        }
        if (userDto.getDivisionId() != null) {
            existingUser.setDivisionId(userDto.getDivisionId());
        }
    }
}
