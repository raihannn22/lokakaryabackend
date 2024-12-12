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
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillReqDto>>>  getAllEmpAchievementSkill() {
        Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillReqDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillReqDto> content = empAchievementSkillServ.getAllEmpAchievementSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpAchievementSkillReqDto>> saveEmpAchievementSkill(@RequestBody EmpAchievementSkillDto empAchievementSkillDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillReqDto> response = new ManagerDto<>();
        EmpAchievementSkillReqDto content = empAchievementSkillServ.createEmpAchievementSkill(empAchievementSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PostMapping("/save-all")
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillReqDto>>> createAllEmpAchievementSkills(@RequestBody List<EmpAchievementSkillDto> empAchievementSkills) {
        Log.info("Start saveAllEmpAchievementSkills in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillReqDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillReqDto> content = empAchievementSkillServ.createAllEmpAchievementSkill(empAchievementSkills);

        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save all data", executionTime));

        Log.info("End saveAllEmpAchievementSkills in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<EmpAchievementSkillReqDto>>  getEmpAchievementSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpAchievementSkillDetail in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillReqDto> response = new ManagerDto<>();
        EmpAchievementSkillReqDto content = empAchievementSkillServ.getEmpAchievementSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAchievementSkillDetail in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpAchievementSkillReqDto>>  updateEmpAchievementSkill(@PathVariable("id") UUID id, @RequestBody EmpAchievementSkillDto empAchievementSkillDto) {
        Log.info("Start updateEmpAchievementSkill in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAchievementSkillReqDto> response = new ManagerDto<>();
        EmpAchievementSkillReqDto content = empAchievementSkillServ.updateEmpAchievementSkill(id, empAchievementSkillDto);

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

    @GetMapping("/get/user/{id}")
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillReqDto>>>  getEmpAchievementSkillByEmpId(@PathVariable("id") UUID id) {
        Log.info("Start getEmpAchievementSkillByEmpId in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillReqDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillReqDto> content = empAchievementSkillServ.getAllEmpAchievementSkillByUser(id);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAchievementSkillByEmpId in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/get/user/{id}/year/{year}")
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillReqDto>>>  getEmpAchievementSkillByEmpIdAndYear(@PathVariable("id") UUID id, @PathVariable("year") Integer year) {
        Log.info("Start getEmpAchievementSkillByEmpId in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillReqDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillReqDto> content = empAchievementSkillServ.getAllEmpAchievementSkillByUserAndYear(id, year);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAchievementSkillByEmpId in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/get/year/{year}")
    public ResponseEntity<ManagerDto<List<EmpAchievementSkillReqDto>>>  getEmpAchievementSkillByYear(@PathVariable("year") Integer year) {
        Log.info("Start getEmpAchievementSkillByYear in EmpAchievementSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAchievementSkillReqDto>> response = new ManagerDto<>();
        List<EmpAchievementSkillReqDto> content = empAchievementSkillServ.getEmpAchievementSkillByYear(year);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAchievementSkillByYear in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
