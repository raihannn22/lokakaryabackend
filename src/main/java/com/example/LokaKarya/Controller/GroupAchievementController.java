package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.LokaKarya.Services.GroupAchievementServ;
import com.example.LokaKarya.util.ServerResponseList;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group-achievement")
public class GroupAchievementController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(GroupAchievementController.class);
@Autowired
    GroupAchievementServ groupAchievementServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<GroupAchievementDto>>>  getAllGroupAchievement() {
        Log.info("Start getAllGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<GroupAchievementDto>> response = new ManagerDto<>();
        List<GroupAchievementDto> content = groupAchievementServ.getAllGroupAchievement();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAchievement in GroupAchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<GroupAchievementDto>>  saveGroupAchievement(@RequestBody GroupAchievementReqDto groupAchievementDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
        GroupAchievementDto content = groupAchievementServ.createGroupAchievement(groupAchievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveGroupAchievement in GroupAchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<GroupAchievementDto>>  getGroupAchievementDetail(@PathVariable("id") UUID id) {
        Log.info("Start getGroupAchievementDetail in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
        GroupAchievementDto content = groupAchievementServ.getGroupAchievementById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getGroupAchievementDetail in GroupAchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<GroupAchievementDto>>  updateGroupAchievement(@PathVariable("id") UUID id, @RequestBody GroupAchievementReqDto groupAchievementDto) {
        Log.info("Start updateGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAchievementDto> response = new ManagerDto<>();
        GroupAchievementDto content = groupAchievementServ.updateGroupAchievement(id, groupAchievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateGroupAchievement in GroupAchievementController");
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
        Log.info("End deleteGroupAchievement in GroupAchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
