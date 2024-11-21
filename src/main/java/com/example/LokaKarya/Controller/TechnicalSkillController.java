package com.example.LokaKarya.Controller;

import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.LokaKarya.Services.TechnicalSkillServ;
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
@RequestMapping("/technical-skill")
public class TechnicalSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(TechnicalSkillController.class);

    @Autowired
    TechnicalSkillServ technicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<TechnicalSkillReqDto>>> getAllTechnicalSkill() {
        Log.info("Start getAllTechnicalSkill in TechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<TechnicalSkillReqDto>> response = new ManagerDto<>();
        List<TechnicalSkillReqDto> content = technicalSkillServ.getAllTechnicalSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllTechnicalSkill in TechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<TechnicalSkillReqDto>> saveTechnicalSkill(@RequestBody TechnicalSkillDto technicalSkillDto) {
        Log.info("Start saveTechnicalSkill in TechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<TechnicalSkillReqDto> response = new ManagerDto<>();
        TechnicalSkillReqDto content = technicalSkillServ.createTechnicalSkill(technicalSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveTechnicalSkill in TechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<TechnicalSkillReqDto>> getTechnicalSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getTechnicalSkillDetail in TechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<TechnicalSkillReqDto> response = new ManagerDto<>();
        TechnicalSkillReqDto content = technicalSkillServ.getTechnicalSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getTechnicalSkillDetail in TechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<TechnicalSkillReqDto>> updateTechnicalSkill(@PathVariable("id") UUID id, @RequestBody TechnicalSkillDto technicalSkillDto) {
        Log.info("Start updateTechnicalSkill in TechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<TechnicalSkillReqDto> response = new ManagerDto<>();
        TechnicalSkillReqDto content = technicalSkillServ.updateTechnicalSkill(id, technicalSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateTechnicalSkill in TechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteTechnicalSkill(@PathVariable("id") UUID id) {
        Log.info("Start deleteTechnicalSkill in TechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = technicalSkillServ.deleteTechnicalSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteTechnicalSkill in TechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
