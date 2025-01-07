package com.example.lokakarya.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuByUserDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuReqDto;
import com.example.lokakarya.Services.AppMenuServ;
import com.example.lokakarya.util.ServerResponseList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appMenu")
public class AppMenuController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AppMenuController.class);

    @Autowired
    AppMenuServ appMenuServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AppMenuReqDto>>> getAllAppMenu() {
        Log.info("Start getAllAppMenu in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppMenuReqDto>> response = new ManagerDto<>();
        List<AppMenuReqDto> content = appMenuServ.getAllAppMenu();
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAllAppMenu in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AppMenuReqDto>> getAppMenuDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAppMenuDetail in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppMenuReqDto> response = new ManagerDto<>();
        AppMenuReqDto content = appMenuServ.getAppMenuById(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAppMenuDetail in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<ManagerDto<AppMenuReqDto>> createAppMenu(@RequestBody AppMenuDto appMenuDto) {
        Log.info("Start createAppMenu in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppMenuReqDto> response = new ManagerDto<>();
        AppMenuReqDto content = appMenuServ.createAppMenu(appMenuDto);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Create data", endTime - startTime));
        Log.info("End createAppMenu in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AppMenuReqDto>> updateAppMenu(@PathVariable("id") UUID id, @RequestBody AppMenuDto appMenuDto) {
        Log.info("Start updateAppMenu in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AppMenuReqDto> response = new ManagerDto<>();
        AppMenuReqDto content = appMenuServ.updateAppMenu(id, appMenuDto);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Update data", endTime - startTime));
        Log.info("End updateAppMenu in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteAppRole(@PathVariable("id") UUID id) {
        Log.info("Start deleteAppRole in AppRoleController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = appMenuServ.deleteAppMenu(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Delete data", endTime - startTime));
        Log.info("End deleteAppRole in AppRoleController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/byUserId/{id}")
    public ResponseEntity<ManagerDto<List<AppMenuByUserDto>>> getAppMenuByUserId(@PathVariable("id") UUID id) {
        Log.info("Start getAppMenuByUserId in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppMenuByUserDto>> response = new ManagerDto<>();
        List<AppMenuByUserDto> content = appMenuServ.getAllAppMenuByUser(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAppMenuByUserId in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/byUsername/{username}")
    public ResponseEntity<ManagerDto<List<AppMenuByUserDto>>> getAppMenuByUsername(@PathVariable("username") String username) {
        Log.info("Start getAppMenuByUserId in AppMenuController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AppMenuByUserDto>> response = new ManagerDto<>();
        List<AppMenuByUserDto> content = appMenuServ.getAllAppMenuByUsername(username);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAppMenuByUserId in AppMenuController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}