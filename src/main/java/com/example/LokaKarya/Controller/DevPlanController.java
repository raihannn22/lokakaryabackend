package com.example.lokakarya.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.DevPlan.DevPlanDto;
import com.example.lokakarya.Dto.DevPlan.DevPlanReqDto;
import com.example.lokakarya.Services.Impl.DevPlanServImpl;
import com.example.lokakarya.util.ServerResponseList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devPlan")
public class DevPlanController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(DevPlanController.class);

    @Autowired
    private DevPlanServImpl devPlanServImpl;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<DevPlanReqDto>>> getAllDevPlan() {
        Log.info("Start getAllDevPlan in DevPlanController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<DevPlanReqDto>> response = new ManagerDto<>();
        List<DevPlanReqDto> content = devPlanServImpl.getAllDevPlan();
        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllDevPlan in DevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<DevPlanReqDto>> getDevPlanDetail(@PathVariable("id") UUID id) {
        Log.info("Start getDevPlanDetail in DevPlanController");
        long startTime = System.currentTimeMillis();

        ManagerDto<DevPlanReqDto> response = new ManagerDto<>();
        DevPlanReqDto content = devPlanServImpl.getDevPlanById(id);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getDevPlanDetail in DevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<DevPlanReqDto>> saveDevPlan(@RequestBody DevPlanDto devPlanDto) {
        Log.info("Start saveDevPlan in DevPlanController");
        long startTime = System.currentTimeMillis();

        ManagerDto<DevPlanReqDto> response = new ManagerDto<>();
        DevPlanReqDto content = devPlanServImpl.createDevPlan(devPlanDto);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveDevPlan in DevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<DevPlanReqDto>> updateDevPlan(@PathVariable("id") UUID id, @RequestBody DevPlanDto devPlanDto) {
        Log.info("Start updateDevPlan in DevPlanController");
        long startTime = System.currentTimeMillis();

        ManagerDto<DevPlanReqDto> response = new ManagerDto<>();
        DevPlanReqDto content = devPlanServImpl.updateDevPlan(id, devPlanDto);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateDevPlan in DevPlanController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteDevPlan(@PathVariable("id") UUID id) {
        Log.info("Start deleteDevPlan in DevPlanController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        boolean content = devPlanServImpl.deleteDevPlan(id);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteDevPlan in DevPlanController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
