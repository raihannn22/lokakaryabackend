package com.example.lokakarya.Controller;


import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.lokakarya.Services.GroupAchievementServ;
import com.example.lokakarya.util.ServerResponseList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group-achievement")
public class GroupAchievementController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(GroupAchievementController.class);
@Autowired
    GroupAchievementServ groupAchievementServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<GroupAchievementReqDto>>>  getAllGroupAchievement() {
        Log.info("Start getAllGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<GroupAchievementReqDto>> response = new ManagerDto<>();
        List<GroupAchievementReqDto> content = groupAchievementServ.getAllGroupAchievement();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAchievement in GroupAchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<GroupAchievementReqDto>>  saveGroupAchievement(@RequestBody GroupAchievementDto groupAchievementDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementReqDto> response = new ManagerDto<>();
        GroupAchievementReqDto content = groupAchievementServ.createGroupAchievement(groupAchievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveGroupAchievement in GroupAchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<GroupAchievementReqDto>>  getGroupAchievementDetail(@PathVariable("id") UUID id) {
        Log.info("Start getGroupAchievementDetail in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementReqDto> response = new ManagerDto<>();
        GroupAchievementReqDto content = groupAchievementServ.getGroupAchievementById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getGroupAchievementDetail in GroupAchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<GroupAchievementReqDto>>  updateGroupAchievement(@PathVariable("id") UUID id, @RequestBody GroupAchievementDto groupAchievementDto) {
        Log.info("Start updateGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementReqDto> response = new ManagerDto<>();
        GroupAchievementReqDto content = groupAchievementServ.updateGroupAchievement(id, groupAchievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateGroupAchievement in GroupAchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteGroupAchievement(@PathVariable("id") UUID id) {
        Log.info("Start deleteGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = groupAchievementServ.deleteGroupAchievement(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteGroupAchievement in GroupAchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
