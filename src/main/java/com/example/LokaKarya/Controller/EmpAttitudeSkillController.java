package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Services.EmpAttitudeSkillServ;
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
@RequestMapping("/emp-attitude-skill")
public class EmpAttitudeSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpAttitudeSkillController.class);
@Autowired
    EmpAttitudeSkillServ empAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpAttitudeSkillReqDto>>>  getAllEmpAttitudeSkill() {
        Log.info("Start getAllEmpAttitudeSkill in EmpAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpAttitudeSkillReqDto>> response = new ManagerDto<>();
        List<EmpAttitudeSkillReqDto> content = empAttitudeSkillServ.getAllEmpAttitudeSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpAttitudeSkill in EmpAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpAttitudeSkillReqDto>> saveEmpAttitudeSkill(@RequestBody EmpAttitudeSkillDto empAttitudeSkillDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAttitudeSkillReqDto> response = new ManagerDto<>();
        EmpAttitudeSkillReqDto content = empAttitudeSkillServ.createEmpAttitudeSkill(empAttitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpAttitudeSkill in EmpAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<EmpAttitudeSkillReqDto>>  getEmpAttitudeSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpAttitudeSkillDetail in EmpAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAttitudeSkillReqDto> response = new ManagerDto<>();
        EmpAttitudeSkillReqDto content = empAttitudeSkillServ.getEmpAttitudeSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpAttitudeSkillDetail in EmpAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpAttitudeSkillReqDto>>  updateEmpAttitudeSkill(@PathVariable("id") UUID id, @RequestBody EmpAttitudeSkillDto empAttitudeSkillDto) {
        Log.info("Start updateEmpAttitudeSkill in EmpAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpAttitudeSkillReqDto> response = new ManagerDto<>();
        EmpAttitudeSkillReqDto content = empAttitudeSkillServ.updateEmpAttitudeSkill(id, empAttitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpAttitudeSkill in EmpAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteEmpAttitudeSkill(@PathVariable("id") UUID id) {
        Log.info("Start deleteEmpAttitudeSkill in EmpAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = empAttitudeSkillServ.deleteEmpAttitudeSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteEmpAttitudeSkill in EmpAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
