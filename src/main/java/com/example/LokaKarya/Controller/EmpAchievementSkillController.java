package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Services.EmpAchievementSkillServ;
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
@RequestMapping("/emp-achievement-skill")
public class EmpAchievementSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpAchievementSkillController.class);
@Autowired
    EmpAchievementSkillServ empAchievementSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillDto>>>  getAllEmpAchievementSkill() {
        Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillDto> content = empAchievementSkillServ.getAllEmpAchievementSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpAchievementSkillDto>> saveEmpAchievementSkill(@RequestBody EmpAchievementSkillReqDto empAchievementSkillDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
        EmpAchievementSkillDto content = empAchievementSkillServ.createEmpAchievementSkill(empAchievementSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<EmpAchievementSkillDto>>  getEmpAchievementSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpAchievementSkillDetail in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
        EmpAchievementSkillDto content = empAchievementSkillServ.getEmpAchievementSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAchievementSkillDetail in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpAchievementSkillDto>>  updateEmpAchievementSkill(@PathVariable("id") UUID id, @RequestBody EmpAchievementSkillReqDto empAchievementSkillDto) {
        Log.info("Start updateEmpAchievementSkill in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillDto> response = new ManagerDto<>();
        EmpAchievementSkillDto content = empAchievementSkillServ.updateEmpAchievementSkill(id, empAchievementSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteEmpAchievementSkill(@PathVariable("id") UUID id) {
        Log.info("Start deleteEmpAchievementSkill in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = empAchievementSkillServ.deleteEmpAchievementSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
