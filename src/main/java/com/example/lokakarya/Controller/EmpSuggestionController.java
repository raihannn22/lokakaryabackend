package com.example.lokakarya.Controller;


import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.lokakarya.Services.EmpSuggestionServ;
import com.example.lokakarya.util.ServerResponseList;
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
    public ResponseEntity<ManagerDto<List<EmpSuggestionReqDto>>>  getAllEmpSuggestion() {
        Log.info("Start getAllEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestionReqDto>> response = new ManagerDto<>();
        List<EmpSuggestionReqDto> content = empSuggestionServ.getAllEmpSuggestion();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<EmpSuggestionReqDto>> saveEmpSuggestion(@RequestBody EmpSuggestionDto empSuggestionDto) {
        Log.info("Start saveEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionReqDto> response = new ManagerDto<>();
        EmpSuggestionReqDto content = empSuggestionServ.createEmpSuggestion(empSuggestionDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveEmpSuggestion in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PostMapping("/save-all")
    public ResponseEntity<ManagerDto<List<EmpSuggestionReqDto>>> createAllEmpSuggestions(@RequestBody List<EmpSuggestionDto> empSuggestions) {
        Log.info("Start saveAllEmpSuggestions in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestionReqDto>> response = new ManagerDto<>();
        List<EmpSuggestionReqDto> content = empSuggestionServ.createAllEmpSuggestion(empSuggestions);

        response.setContent(content);
        response.setTotalRows(content.size());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save all data", executionTime));

        Log.info("End saveAllEmpSuggestions in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestionReqDto>>  getEmpSuggestionDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpSuggestionDetail in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionReqDto> response = new ManagerDto<>();
        EmpSuggestionReqDto content = empSuggestionServ.getEmpSuggestionById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getEmpSuggestionDetail in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestionReqDto>>  updateEmpSuggestion(@PathVariable("id") UUID id, @RequestBody EmpSuggestionDto empSuggestionDto) {
        Log.info("Start updateEmpSuggestion in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestionReqDto> response = new ManagerDto<>();
        EmpSuggestionReqDto content = empSuggestionServ.updateEmpSuggestion(id, empSuggestionDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateEmpSuggestion in EmpAchievementSkillController");
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<ManagerDto<List<EmpSuggestionReqDto>>> getEmpSuggestionsByUserId(@PathVariable UUID userId) {
        Log.info("Start getEmpSuggestionsByUserId in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestionReqDto>> response = new ManagerDto<>();
        List<EmpSuggestionReqDto> content = empSuggestionServ.getEmpSuggestionByUserId(userId);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));

        Log.info("End getEmpSuggestionsByUserId in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/year/{assessmentYear}")
    public ResponseEntity<ManagerDto<List<EmpSuggestionReqDto>>> getEmpSuggestionsByUserIdAndYear(
            @PathVariable UUID userId, 
            @PathVariable Integer assessmentYear) {
        Log.info("Start getEmpSuggestionsByUserIdAndYear in EmpSuggestionController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestionReqDto>> response = new ManagerDto<>();
        List<EmpSuggestionReqDto> content = empSuggestionServ.getEmpSuggestionsByUserIdAndYear(userId, assessmentYear);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));

        Log.info("End getEmpSuggestionsByUserIdAndYear in EmpSuggestionController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
