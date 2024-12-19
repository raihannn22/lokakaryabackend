package com.example.lokakarya.Controller;


import com.example.lokakarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Services.AppUserRoleServ;
import com.example.lokakarya.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appUserRole")
public class AppUserRoleController extends ServerResponseList {

    private final Logger Log = LoggerFactory.getLogger(AppUserRoleController.class);

    @Autowired
    AppUserRoleServ appUserRoleServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AppUserRoleReqDto>>> getAllAppUserRole() {
        Log.info("Start getAllAppUserRole in AppUserRoleController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppUserRoleReqDto>> response = new ManagerDto<>();
        List<AppUserRoleReqDto> content = appUserRoleServ.getAllAppUserRoleMenu();
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAllAppUserRole in AppUserRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AppUserRoleReqDto>> getAppUserRoleDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAppUserRoleDetail in AppUserRoleController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppUserRoleReqDto> response = new ManagerDto<>();
        AppUserRoleReqDto content = appUserRoleServ.getAppUserRoleById(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAppUserRoleDetail in AppUserRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
