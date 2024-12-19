package com.example.lokakarya.Controller;


import com.example.lokakarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Services.AppRoleMenuServ;
import com.example.lokakarya.util.ServerResponseList;
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
    private Logger Log = LoggerFactory.getLogger(AppRoleMenuController.class);

    @Autowired
    private AppRoleMenuServ appRoleMenuServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AppRoleMenuReqDto>>> getAllAppRoleMenu() {
        Log.info("Start getAllAppRoleMenu in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppRoleMenuReqDto>> response = new ManagerDto<>();
        List<AppRoleMenuReqDto> content = appRoleMenuServ.getAllAppRoleMenu();
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAllAppRoleMenu in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AppRoleMenuReqDto>> getAppRoleMenuDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAppRoleMenuDetail in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppRoleMenuReqDto> response = new ManagerDto<>();
        AppRoleMenuReqDto content = appRoleMenuServ.getAppRoleMenuById(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAppRoleMenuDetail in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping ("/create")
    public ResponseEntity<ManagerDto<Void>> createAppRoleMenu(@RequestBody Map<UUID, List<UUID>> appRoleMenuReqDto) {
        Log.info("Start createAppRoleMenu in AppRoleMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Void> response = new ManagerDto<>();
         appRoleMenuServ.createAppRoleMenu(appRoleMenuReqDto);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Create data", endTime - startTime));
        Log.info("End createAppRoleMenu in AppRoleMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
