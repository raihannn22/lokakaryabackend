package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.LokaKarya.Entity.AppUserRole;
import com.example.LokaKarya.Repository.AppUserRoleRepo;
import com.example.LokaKarya.Services.AppUserRoleServ;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppUserRoleServImpl implements AppUserRoleServ {
    Logger Log = LoggerFactory.getLogger(AppUserRoleServImpl.class);

    @Autowired
    private AppUserRoleRepo appUserRoleRepo;

    @Override
    public List<AppUserRoleReqDto> getAllAppUserRoleMenu() {
        List<AppUserRole> response = appUserRoleRepo.findAll();
        List<AppUserRoleReqDto> appUserRoleReqDto = new ArrayList<>();
        for (AppUserRole appUserRole : response) {
            Hibernate.initialize(appUserRole.getAppRole());
            Hibernate.initialize(appUserRole.getUser());
            appUserRoleReqDto.add(AppUserRoleReqDto.fromEntity(appUserRole));
        }
        return appUserRoleReqDto;
    }

    @Override
    public AppUserRoleReqDto getAppUserRoleById(UUID id) {
        AppUserRole appUserRole = appUserRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppUserRole not found"));
        return AppUserRoleReqDto.fromEntity(appUserRole);
    }
}
