package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppMenu.AppMenuReqDto;
import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuDto;
import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.LokaKarya.Entity.AppMenu;
import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.AppRoleMenu;
import com.example.LokaKarya.Repository.AppMenuRepo;
import com.example.LokaKarya.Repository.AppRoleMenuRepo;
import com.example.LokaKarya.Repository.AppRoleRepo;
import com.example.LokaKarya.Services.AppRoleMenuServ;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AppRoleMenuServImpl implements AppRoleMenuServ {
    private final Logger Log = LoggerFactory.getLogger(AppRoleMenuServImpl.class);
    @Autowired
    private AppRoleMenuRepo appRoleMenuRepo;

    @Autowired
    private AppMenuRepo appMenuRepo;

    @Autowired
    private AppRoleRepo appRoleRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<AppRoleMenuReqDto> getAllAppRoleMenu() {
        List<AppRoleMenu> response = appRoleMenuRepo.findAll();
        List<AppRoleMenuReqDto> appRoleMenuReqDto = new ArrayList<>();
        for (AppRoleMenu appRoleMenu : response) {
            appRoleMenuReqDto.add(AppRoleMenuReqDto.fromEntity(appRoleMenu));
        }
        return appRoleMenuReqDto;
    }

    @Override
    public AppRoleMenuReqDto getAppRoleMenuById(UUID id) {
        AppRoleMenu appRoleMenu = appRoleMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRoleMenu not found"));
        return AppRoleMenuReqDto.fromEntity(appRoleMenu);
    }

    @Transactional
    @Override
    public void createAppRoleMenu(Map<UUID, List<UUID>> appRoleMenuDto) {
        appRoleMenuRepo.deleteAll();
        entityManager.flush();
        for (Map.Entry<UUID, List<UUID>> entry : appRoleMenuDto.entrySet()) {
            AppRole appRole = appRoleRepo.findById(entry.getKey()).orElseThrow(() -> new RuntimeException("AppRole not found"));
            for (UUID appMenuId : entry.getValue()) {
                AppMenu appMenu = appMenuRepo.findById(appMenuId).orElseThrow(() -> new RuntimeException("AppMenu not found"));
                AppRoleMenu appRoleMenu = new AppRoleMenu();
                appRoleMenu.setAppMenu(appMenu);
                appRoleMenu.setAppRole(appRole);
                appRoleMenuRepo.save(appRoleMenu);
            }
        }
    }
}
