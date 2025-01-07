package com.example.lokakarya.Services.Impl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.lokakarya.Entity.AppMenu;
import com.example.lokakarya.Entity.AppRole;
import com.example.lokakarya.Entity.AppRoleMenu;
import com.example.lokakarya.Repository.AppMenuRepo;
import com.example.lokakarya.Repository.AppRoleMenuRepo;
import com.example.lokakarya.Repository.AppRoleRepo;
import com.example.lokakarya.Services.AppRoleMenuServ;
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
        Log.info("Start getAllAppRoleMenu in AppRoleMenuServImpl");
        List<AppRoleMenu> response = appRoleMenuRepo.findAll();
        List<AppRoleMenuReqDto> appRoleMenuReqDto = new ArrayList<>();
        for (AppRoleMenu appRoleMenu : response) {
            appRoleMenuReqDto.add(AppRoleMenuReqDto.fromEntity(appRoleMenu));
        }
        Log.info("End getAllAppRoleMenu in AppRoleMenuServImpl");
        return appRoleMenuReqDto;
    }

    @Override
    public AppRoleMenuReqDto getAppRoleMenuById(UUID id) {
        Log.info("Start getAppRoleMenuById in AppRoleMenuServImpl");
        AppRoleMenu appRoleMenu = appRoleMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRoleMenu not found"));
        Log.info("End getAppRoleMenuById in AppRoleMenuServImpl");
        return AppRoleMenuReqDto.fromEntity(appRoleMenu);
    }

    @Transactional
    @Override
    public void createAppRoleMenu(Map<UUID, List<UUID>> appRoleMenuDto) {
        Log.info("Start createAppRoleMenu in AppRoleMenuServImpl");
        appRoleMenuRepo.deleteAll();
        entityManager.flush();
        for (Map.Entry<UUID, List<UUID>> entry : appRoleMenuDto.entrySet()) {
            AppRole appRole = appRoleRepo.findById(entry.getKey()).orElseThrow(() -> new RuntimeException("AppRole not found"));
            for (UUID appMenuId : entry.getValue()) {
                AppMenu appMenu = appMenuRepo.findById(appMenuId).orElseThrow(() -> new RuntimeException("AppMenu not found"));
                AppRoleMenu appRoleMenu = new AppRoleMenu();
                appRoleMenu.setAppMenu(appMenu);
                appRoleMenu.setAppRole(appRole);
                Log.info("End createAppRoleMenu in AppRoleMenuServImpl");
                appRoleMenuRepo.save(appRoleMenu);
            }
        }
    }
}