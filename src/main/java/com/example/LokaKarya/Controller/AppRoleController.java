package com.example.lokakarya.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.AppRole.AppRoleDto;
import com.example.lokakarya.Dto.AppRole.AppRoleReqDto;
import com.example.lokakarya.Services.AppRoleServ;
import com.example.lokakarya.util.ServerResponseList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appRole")
public class AppRoleController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AppRoleController.class);

    @Autowired
    AppRoleServ appRoleServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AppRoleReqDto>>> getAllAppRole() {
        Log.info("Start getAllAppRole in AppRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AppRoleReqDto>> response = new ManagerDto<>();
        List<AppRoleReqDto> content = appRoleServ.getAllAppRole();

        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success get all data", endTime - startTime));
        Log.info("End getAllAppRole in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AppRoleReqDto>> getAppRoleDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAppRoleDetail in AppRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AppRoleReqDto> response = new ManagerDto<>();
        AppRoleReqDto content = appRoleServ.getAppRoleById(id);
        response.setContent(content);

        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success get data with Id", endTime - startTime));
        Log.info("End getAppRoleDetail in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<AppRoleReqDto>> saveAppRole(@RequestBody AppRoleDto appRoleDto) {
        Log.info("Start saveAppRole in AppRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AppRoleReqDto> response = new ManagerDto<>();
        AppRoleReqDto content = appRoleServ.createAppRole(appRoleDto);
        response.setContent(content);

        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Save data", endTime - startTime));
        Log.info("End saveAppRole in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AppRoleReqDto>> updateAppRole(@PathVariable("id") UUID id, @RequestBody AppRoleDto appRoleDto) {
        Log.info("Start updateAppRole in AppRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AppRoleReqDto> response = new ManagerDto<>();
        AppRoleReqDto content = appRoleServ.updateAppRole(id, appRoleDto);
        response.setContent(content);

        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Update data", endTime - startTime));
        Log.info("End updateAppRole in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteAppRole(@PathVariable("id") UUID id) {
        Log.info("Start deleteAppRole in AppRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = appRoleServ.deleteAppRole(id);
        response.setContent(content);

        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Delete data", endTime - startTime));
        Log.info("End deleteAppRole in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
