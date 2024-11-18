package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Services.AchievementServ;
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
@RequestMapping("/achievement")
public class AchievementController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AchievementController.class);
@Autowired
    AchievementServ achievementServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<AchievementDto>>>  getAllAchievement() {
        Log.info("Start getAllAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AchievementDto>> response = new ManagerDto<>();
        List<AchievementDto> content = achievementServ.getAllAchievement();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<AchievementDto>>  saveAchievement(@RequestBody AchievementReqDto achievementDto) {
        Log.info("Start saveAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementDto> response = new ManagerDto<>();
        AchievementDto content = achievementServ.createAchievement(achievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<AchievementDto>>  getAchievementDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAchievementDetail in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementDto> response = new ManagerDto<>();
        AchievementDto content = achievementServ.getAchievementById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAchievementDetail in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AchievementDto>>  updateAchievement(@PathVariable("id") UUID id, @RequestBody AchievementReqDto achievementDto) {
        Log.info("Start updateAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementDto> response = new ManagerDto<>();
        AchievementDto content = achievementServ.updateAchievement(id, achievementDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteAchievement(@PathVariable("id") UUID id) {
        Log.info("Start deleteAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = achievementServ.deleteAchievement(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
