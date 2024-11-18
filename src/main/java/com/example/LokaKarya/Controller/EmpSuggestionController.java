package com.example.LokaKarya.Controller;


import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.LokaKarya.Services.EmpSuggestionServ;
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
@RequestMapping("/emp-suggestion")
public class EmpSuggestionController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpSuggestionController.class);
@Autowired
    EmpSuggestionServ empSuggestionServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpSuggestionDto>>>  getAllEmpSuggestion() {
        Log.info("Start getAllEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestionDto>> response = new ManagerDto<>();
        List<EmpSuggestionDto> content = empSuggestionServ.getAllEmpSuggestion();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpSuggestion in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpSuggestionDto>> saveEmpSuggestion(@RequestBody EmpSuggestionReqDto empSuggestionDto) {
        Log.info("Start saveGroupAchievement in GroupAchievementController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
        EmpSuggestionDto content = empSuggestionServ.createEmpSuggestion(empSuggestionDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpSuggestion in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestionDto>>  getEmpSuggestionDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpSuggestionDetail in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
        EmpSuggestionDto content = empSuggestionServ.getEmpSuggestionById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpSuggestionDetail in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestionDto>>  updateEmpSuggestion(@PathVariable("id") UUID id, @RequestBody EmpSuggestionReqDto empSuggestionDto) {
        Log.info("Start updateEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionDto> response = new ManagerDto<>();
        EmpSuggestionDto content = empSuggestionServ.updateEmpSuggestion(id, empSuggestionDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpSuggestion in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteEmpSuggestion(@PathVariable("id") UUID id) {
        Log.info("Start deleteEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = empSuggestionServ.deleteEmpSuggestion(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteEmpSuggestion in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }



}
