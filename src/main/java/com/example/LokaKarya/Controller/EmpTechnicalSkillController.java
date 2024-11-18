package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Services.EmpTechnicalSkillServ;
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
@RequestMapping("/emp-technical-skill")
public class EmpTechnicalSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillController.class);
@Autowired
    EmpTechnicalSkillServ empTechnicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpTechnicalSkillDto>>>  getAllEmpTechnicalSkill() {
        Log.info("Start getAllEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechnicalSkillDto>> response = new ManagerDto<>();
        List<EmpTechnicalSkillDto> content = empTechnicalSkillServ.getAllEmpTechnicalSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpTechnicalSkill in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillDto>> saveEmpTechnicalSkill(@RequestBody EmpTechnicalSkillReqDto empTechnicalSkillDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
        EmpTechnicalSkillDto content = empTechnicalSkillServ.createEmpTechnicalSkill(empTechnicalSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpTechnicalSkill in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillDto>>  getEmpTechnicalSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpTechnicalSkillDetail in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
        EmpTechnicalSkillDto content = empTechnicalSkillServ.getEmpTechnicalSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpTechnicalSkillDetail in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillDto>>  updateEmpTechnicalSkill(@PathVariable("id") UUID id, @RequestBody EmpTechnicalSkillReqDto empTechnicalSkillDto) {
        Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechnicalSkillDto> response = new ManagerDto<>();
        EmpTechnicalSkillDto content = empTechnicalSkillServ.updateEmpTechnicalSkill(id, empTechnicalSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteEmpTechnicalSkill(@PathVariable("id") UUID id) {
        Log.info("Start deleteEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = empTechnicalSkillServ.deleteEmpTechnicalSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteEmpTechnicalSkill in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
