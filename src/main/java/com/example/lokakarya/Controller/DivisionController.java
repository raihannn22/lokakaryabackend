package com.example.lokakarya.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.Division.DivisionDto;
import com.example.lokakarya.Dto.Division.DivisionReqDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.lokakarya.Services.AssessmentSummaryServ;
import com.example.lokakarya.Services.DivisionServ;
import com.example.lokakarya.util.ServerResponseList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/division")
public class DivisionController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryController.class);

    @Autowired
    DivisionServ divisionServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<DivisionReqDto>>> getAllDivision() {
        Log.info("Start getAllDivision in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<DivisionReqDto>> response = new ManagerDto<>();
        List<DivisionReqDto> content = divisionServ.getAllDivision();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllDivision in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/paginated")
    public ResponseEntity<ManagerDto<List<DivisionReqDto>>> getPaginatedDivision(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "divisionName") String sort, // Kolom default untuk sorting
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String searchKeyword) { // Arah sorting default
        Log.info("Start getPaginatedDivision in DivisionController");
        long startTime = System.currentTimeMillis();
        long totalRecords = divisionServ.count();

        ManagerDto<List<DivisionReqDto>> response = new ManagerDto<>();
        List<DivisionReqDto> content = divisionServ.getPaginatedDivision(page, size, sort, direction, searchKeyword);

        response.setContent(content);
        response.setTotalData(totalRecords);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getPaginatedDivision in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<DivisionReqDto>> getDivisionDetail(@PathVariable("id") UUID id) {
        Log.info("Start getDivisionDetail in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<DivisionReqDto> response = new ManagerDto<>();
        DivisionReqDto content = divisionServ.getDivisionById(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getDivisionDetail in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<DivisionReqDto>> saveDivision(@RequestBody DivisionDto divisionDto) {
        Log.info("Start saveDivision in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<DivisionReqDto> response = new ManagerDto<>();
        DivisionReqDto content = divisionServ.createDivision(divisionDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveDivision in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<DivisionReqDto>> updateDivision(@PathVariable("id") UUID id, @RequestBody DivisionDto divisionDto) {
        Log.info("Start updateDivision in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<DivisionReqDto> response = new ManagerDto<>();
        DivisionReqDto content = divisionServ.updateDivision(id, divisionDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateDivision in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteDivision(@PathVariable("id") UUID id) {
        Log.info("Start deleteDivision in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = divisionServ.deleteDivision(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteDivision in DivisionController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
