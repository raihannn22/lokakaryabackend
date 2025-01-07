package com.example.lokakarya.Services.Impl;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.lokakarya.Entity.AppUserRole;
import com.example.lokakarya.Repository.AppUserRoleRepo;
import com.example.lokakarya.Services.AppUserRoleServ;
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
        Log.info("Start getAllAppUserRoleMenu in AppUserRoleServImpl");
        List<AppUserRole> response = appUserRoleRepo.findAll();
        List<AppUserRoleReqDto> appUserRoleReqDto = new ArrayList<>();
        for (AppUserRole appUserRole : response) {
            Hibernate.initialize(appUserRole.getAppRole());
            Hibernate.initialize(appUserRole.getUser());
            appUserRoleReqDto.add(AppUserRoleReqDto.fromEntity(appUserRole));
        }
        Log.info("End getAllAppUserRoleMenu in AppUserRoleServImpl");
        return appUserRoleReqDto;
    }

    @Override
    public AppUserRoleReqDto getAppUserRoleById(UUID id) {
        Log.info("Start getAppUserRoleById in AppUserRoleServImpl");
        AppUserRole appUserRole = appUserRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppUserRole not found"));
        Log.info("End getAppUserRoleById in AppUserRoleServImpl");
        return AppUserRoleReqDto.fromEntity(appUserRole);
    }
}