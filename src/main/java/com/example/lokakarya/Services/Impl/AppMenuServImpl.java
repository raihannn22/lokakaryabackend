package com.example.lokakarya.Services.Impl;

import com.example.lokakarya.Dto.AppMenu.AppMenuByUserDto;
import com.example.lokakarya.util.GetUserUtil;
import com.example.lokakarya.Dto.AppMenu.AppMenuDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuReqDto;
import com.example.lokakarya.Entity.AppMenu;
import com.example.lokakarya.Repository.AppMenuRepo;
import com.example.lokakarya.Services.AppMenuServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppMenuServImpl implements AppMenuServ {
    private final Logger Log = LoggerFactory.getLogger(AppMenuServ.class);

    @Autowired
    AppMenuRepo appMenuRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<AppMenuReqDto> getAllAppMenu() {
        Log.info("Start getAllAppMenu in AppMenuServImpl");
        List<AppMenu> response = appMenuRepo.findAll();
        List<AppMenuReqDto> appMenuReqDto = new ArrayList<>();
        for (AppMenu appMenu : response) {
            appMenuReqDto.add(AppMenuReqDto.fromEntity(appMenu));
        }
        Log.info("End getAllAppMenu in AppMenuServImpl");
        return appMenuReqDto;
    }

    @Override
    public AppMenuReqDto getAppMenuById(UUID id) {
       Log.info("Start getAppMenuById in AppMenuServImpl");
       AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
       Log.info("End getAppMenuById in AppMenuServImpl");
       return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public AppMenuReqDto createAppMenu(AppMenuDto appMenuDto) {
        Log.info("Start createAppMenu in AppMenuServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        AppMenu appMenu = AppMenuDto.toEntity(appMenuDto, null, null, currentUserId, new java.util.Date());
        appMenuRepo.save(appMenu);
        Log.info("End createAppMenu in AppMenuServImpl");
        return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto) {
        Log.info("Start updateAppMenu in AppMenuServImpl");
        AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        AppMenu appMenuUpdate = AppMenuDto.toEntity(appMenuDto, currentUserId, new java.util.Date(), appMenu.getCreatedBy() ,appMenu.getCreatedAt());
        appMenuUpdate.setId(id);
        appMenuRepo.save(appMenuUpdate);
        Log.info("End updateAppMenu in AppMenuServImpl");
        return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public Boolean deleteAppMenu(UUID id) {
        Log.info("Start deleteAppMenu in AppMenuServImpl");
        AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
        appMenuRepo.delete(appMenu);
        Log.info("End deleteAppMenu in AppMenuServImpl");
        return true;
    }

    @Override
    public List<AppMenuByUserDto> getAllAppMenuByUser(UUID id) {
        Log.info("Start getAllAppMenuByUser in AppMenuServImpl");
        List<Object[]> menuList = appMenuRepo.getAppMenuByUserId(id);
        List<AppMenuByUserDto> appMenuByUserDto = new ArrayList<>();
        for (Object[] e : menuList) {
            AppMenuByUserDto appMenuByUserDto1 = new AppMenuByUserDto();
            appMenuByUserDto1.setId(bytesToUUID((byte[]) e[0]));
            appMenuByUserDto1.setMenuName((String) e[1]);
            appMenuByUserDto1.setRoleName((String) e[2]);
            appMenuByUserDto.add(appMenuByUserDto1);
        }
        Log.info("End getAllAppMenuByUser in AppMenuServImpl");
        return appMenuByUserDto;
    }

    @Override
    public List<AppMenuByUserDto> getAllAppMenuByUsername(String username) {
        Log.info("Start getAllAppMenuByUsername in AppMenuServImpl");
        List<Object[]> menuList = appMenuRepo.getAppMenuByUsername(username);
        List<AppMenuByUserDto> appMenuByUserDto = new ArrayList<>();
        for (Object[] e : menuList) {
            AppMenuByUserDto appMenuByUserDto1 = new AppMenuByUserDto();
            appMenuByUserDto1.setId(bytesToUUID((byte[]) e[0]));
            appMenuByUserDto1.setMenuName((String) e[1]);
            appMenuByUserDto1.setRoleName((String) e[2]);
            appMenuByUserDto.add(appMenuByUserDto1);
        }
        Log.info("End getAllAppMenuByUsername in AppMenuServImpl");
        return appMenuByUserDto;
    }

    private UUID bytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}
