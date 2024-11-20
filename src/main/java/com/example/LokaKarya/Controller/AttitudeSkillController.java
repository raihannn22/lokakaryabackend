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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Services.AttitudeSkillServ;
import com.example.LokaKarya.util.ServerResponseList;

@RestController
@RequestMapping("/attitude-skill")
public class AttitudeSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AttitudeSkillController.class);
@Autowired
    AttitudeSkillServ attitudeSkillServ;

    @GetMapping("/get-all")
    public ResponseEntity<ManagerDto<List<AttitudeSkillReqDto>>>  getAllAttitudeSkill() {
        Log.info("Start getAllAttitudeSkill in AttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AttitudeSkillReqDto>> response = new ManagerDto<>();
        List<AttitudeSkillReqDto> content = attitudeSkillServ.getAllAttitudeSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAttitudeSkill in AttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<AttitudeSkillReqDto>>  saveAttitudeSkill(@RequestBody AttitudeSkillDto attitudeSkillDto) {
        Log.info("Start saveAttitudeSkill in AttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AttitudeSkillReqDto> response = new ManagerDto<>();
        AttitudeSkillReqDto content = attitudeSkillServ.createAttitudeSkill(attitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAttitudeSkill in AttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AttitudeSkillReqDto>>  getAttitudeSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAttitudeSkillDetail in AttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AttitudeSkillReqDto> response = new ManagerDto<>();
        AttitudeSkillReqDto content = attitudeSkillServ.getAttitudeSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAttitudeSkillDetail in AttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AttitudeSkillReqDto>>  updateAttitudeSkill(@PathVariable("id") UUID id, @RequestBody AttitudeSkillDto attitudeSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AttitudeSkillReqDto> response = new ManagerDto<>();
        AttitudeSkillReqDto content = attitudeSkillServ.updateAttitudeSkill(id, attitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAttitudeSkill in AttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping
    public ResponseEntity<ManagerDto<Boolean>> deleteAttitudeSkill(@RequestParam("id") UUID id) {
        Log.info("Start deleteAttitudeSkill in AttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = attitudeSkillServ.deleteAttitudeSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteAttitudeSkill in AttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
