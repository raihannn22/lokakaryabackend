package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuDto;
import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Services.AppRoleMenuServ;
import com.example.LokaKarya.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/appRoleMenu")
public class AppRoleMenuController extends ServerResponseList {
    private Logger Logger = LoggerFactory.getLogger(AppRoleMenuController.class);

    @Autowired
    private AppRoleMenuServ appRoleMenuServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AppRoleMenuReqDto>>> getAllAppRoleMenu() {
        Logger.info("Start getAllAppRoleMenu in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppRoleMenuReqDto>> response = new ManagerDto<>();
        List<AppRoleMenuReqDto> content = appRoleMenuServ.getAllAppRoleMenu();
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Logger.info("End getAllAppRoleMenu in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AppRoleMenuReqDto>> getAppRoleMenuDetail(@PathVariable("id") UUID id) {
        Logger.info("Start getAppRoleMenuDetail in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppRoleMenuReqDto> response = new ManagerDto<>();
        AppRoleMenuReqDto content = appRoleMenuServ.getAppRoleMenuById(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Logger.info("End getAppRoleMenuDetail in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping ("/create")
    public ResponseEntity<ManagerDto<Void>> createAppRoleMenu(@RequestBody Map<UUID, List<UUID>> appRoleMenuReqDto) {
        Logger.info("Start createAppRoleMenu in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Void> response = new ManagerDto<>();
         appRoleMenuServ.createAppRoleMenu(appRoleMenuReqDto);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Create data", endTime - startTime));
        Logger.info("End createAppRoleMenu in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
