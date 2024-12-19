package com.example.lokakarya.Controller;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.lokakarya.Services.EmpTechnicalSkillServ;
import com.example.lokakarya.util.ServerResponseList;

@RestController
@RequestMapping("/emp-technical-skill")
public class EmpTechnicalSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillController.class);
@Autowired
    EmpTechnicalSkillServ empTechnicalSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpTechnicalSkillReqDto>>>  getAllEmpTechnicalSkill() {
        Log.info("Start getAllEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechnicalSkillReqDto>> response = new ManagerDto<>();
        List<EmpTechnicalSkillReqDto> content = empTechnicalSkillServ.getAllEmpTechnicalSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpTechnicalSkill in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillReqDto>> saveEmpTechnicalSkill(@RequestBody EmpTechnicalSkillDto empTechnicalSkillDto) {
        Log.info("Start saveEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechnicalSkillReqDto> response = new ManagerDto<>();
        EmpTechnicalSkillReqDto content = empTechnicalSkillServ.createEmpTechnicalSkill(empTechnicalSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpTechnicalSkill in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PostMapping("/save-all")
    public ResponseEntity<ManagerDto<List<EmpTechnicalSkillReqDto>>> createAllEmpTechnicalSkills(@RequestBody List<EmpTechnicalSkillDto> empTechnicalSkills) {
        Log.info("Start saveAllEmpTechnicalSkills in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechnicalSkillReqDto>> response = new ManagerDto<>();
        List<EmpTechnicalSkillReqDto> content = empTechnicalSkillServ.createAllEmpTechnicalSkill(empTechnicalSkills);

        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save all data", executionTime));

        Log.info("End saveAllEmpTechnicalSkills in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillReqDto>>  getEmpTechnicalSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpTechnicalSkillDetail in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechnicalSkillReqDto> response = new ManagerDto<>();
        EmpTechnicalSkillReqDto content = empTechnicalSkillServ.getEmpTechnicalSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpTechnicalSkillDetail in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ManagerDto<List<EmpTechnicalSkillReqDto>>> getEmpTechnicalSkillsByUserId(@PathVariable UUID userId) {
        Log.info("Start getEmpTechnicalSkillsByUserId in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechnicalSkillReqDto>> response = new ManagerDto<>();
        List<EmpTechnicalSkillReqDto> content = empTechnicalSkillServ.getEmpTechnicalSkillByUserId(userId);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));

        Log.info("End getEmpTechnicalSkillsByUserId in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    
    


@GetMapping("/user/{userId}/year/{assessmentYear}")
    public ResponseEntity<ManagerDto<List<EmpTechnicalSkillReqDto>>> getEmpTechnicalSkillsByUserIdAndYear(
            @PathVariable UUID userId, 
            @PathVariable Integer assessmentYear) {
        Log.info("Start getEmpTechnicalSkillsByUserIdAndYear in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechnicalSkillReqDto>> response = new ManagerDto<>();
        List<EmpTechnicalSkillReqDto> content = empTechnicalSkillServ.getEmpTechnicalSkillsByUserIdAndYear(userId, assessmentYear);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));

        Log.info("End getEmpTechnicalSkillsByUserIdAndYear in EmpTechnicalSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpTechnicalSkillReqDto>>  updateEmpTechnicalSkill(@PathVariable("id") UUID id, @RequestBody EmpTechnicalSkillDto empTechnicalSkillDto) {
        Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<EmpTechnicalSkillReqDto> response = new ManagerDto<>();
        EmpTechnicalSkillReqDto content = empTechnicalSkillServ.updateEmpTechnicalSkill(id, empTechnicalSkillDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
    
    @DeleteMapping
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