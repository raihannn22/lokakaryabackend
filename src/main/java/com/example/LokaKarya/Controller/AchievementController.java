package com.example.LokaKarya.Controller;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Services.AchievementServ;
import com.example.LokaKarya.util.ServerResponseList;

@RestController
@RequestMapping("/achievement")
public class AchievementController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AchievementController.class);

    @Autowired
    AchievementServ achievementServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<AchievementReqDto>>> getAllAchievement() {
        Log.info("Start getAllAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AchievementReqDto>> response = new ManagerDto<>();
        List<AchievementReqDto> content = achievementServ.getAllAchievement();
        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAchievement in AchievementControllerController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AchievementReqDto>> getAchievementDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAchievementDetail in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementReqDto> response = new ManagerDto<>();
        AchievementReqDto content = achievementServ.getAchievementById(id);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAchievementDetail in AchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<ManagerDto<List<AchievementReqDto>>> getAchievementByGroupId(@PathVariable("groupId") UUID groupId) {
        Log.info("Start getAchievementsByGroup in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AchievementReqDto>> response = new ManagerDto<>();
        List<AchievementReqDto> content = achievementServ.getAchievementsByGroupId(groupId); // pastikan method di service mengembalikan list
        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAchievementsByGroup in AchievementController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





    @PutMapping("/save")
    public ResponseEntity<ManagerDto<AchievementReqDto>> saveAchievement (@RequestBody AchievementDto achievementDto) {
        Log.info("Start saveAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementReqDto> response = new ManagerDto<>();
        AchievementReqDto content = achievementServ.createAchievement(achievementDto);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AchievementReqDto>> updateAchievement(@PathVariable("id") UUID id, @RequestBody AchievementDto achievementDto) {
        Log.info("Start updateAchievement in AchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AchievementReqDto> response = new ManagerDto<>();
        AchievementReqDto content = achievementServ.updateAchievement(id, achievementDto);
        response.setContent(content);
        response.setTotalRows(1);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAchievement in AchievementController");
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
