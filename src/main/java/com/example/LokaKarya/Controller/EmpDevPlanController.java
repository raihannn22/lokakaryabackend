package com.example.LokaKarya.Controller;

import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanReqDto;
import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Services.EmpDevPlanServ;
import com.example.LokaKarya.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/empDevPlan")
public class EmpDevPlanController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpDevPlanController.class);

    @Autowired
    EmpDevPlanServ empDevPlanServ;

    @PostMapping("/create")
    public ResponseEntity<ManagerDto<List<EmpDevPlanReqDto>>> saveEmpDevPlan(@RequestBody List<EmpDevPlanDto> empDevPlanDto) {
        Log.info("Start saveEmpDevPlan in EmpDevPlanController");
        long StartTime = System.currentTimeMillis();
        ManagerDto<List<EmpDevPlanReqDto>> response = new ManagerDto<>();
        List<EmpDevPlanReqDto> content = empDevPlanServ.createEmpDevPlans(empDevPlanDto);
        response.setContent(content);
        long EndTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Create data", EndTime - StartTime));
        Log.info("End saveEmpDevPlan in EmpDevPlanController, time: " + (EndTime - StartTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<EmpDevPlanReqDto>>> getAllEmpDevPlan() {
        Log.info("Start getAllEmpDevPlan in EmpDevPlanController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<EmpDevPlanReqDto>> response = new ManagerDto<>();
        List<EmpDevPlanReqDto> content = empDevPlanServ.getAllEmpDevPlan();
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getAllEmpDevPlan in EmpDevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<ManagerDto<EmpDevPlanReqDto>> getEmpDevPlanDetail(@PathVariable("id") UUID id) {
        Log.info("Start getEmpDevPlanDetail in EmpDevPlanController");
        long startTime = System.currentTimeMillis();
        ManagerDto<EmpDevPlanReqDto> response = new ManagerDto<>();
        EmpDevPlanReqDto content = empDevPlanServ.getEmpDevPlanById(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getEmpDevPlanDetail in EmpDevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpDevPlanReqDto>> updateEmpDevPlan(@PathVariable("id") UUID id, @RequestBody EmpDevPlanDto empDevPlanDto) {
        Log.info("Start updateEmpDevPlan in EmpDevPlanController");
        long startTime = System.currentTimeMillis();
        ManagerDto<EmpDevPlanReqDto> response = new ManagerDto<>();
        EmpDevPlanReqDto content = empDevPlanServ.updateEmpDevPlan(id, empDevPlanDto);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Update data", endTime - startTime));
        Log.info("End updateEmpDevPlan in EmpDevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteEmpDevPlan(@PathVariable("id") UUID id) {
        Log.info("Start deleteEmpDevPlan in EmpDevPlanController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = empDevPlanServ.deleteEmpDevPlan(id);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Delete data", endTime - startTime));
        Log.info("End deleteEmpDevPlan in EmpDevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}/{year}")
    public ResponseEntity<ManagerDto<List<EmpDevPlanReqDto>>> getEmpDevPlanByYear(@PathVariable("id") UUID id, @PathVariable("year") Integer year) {
        Log.info("Start getEmpDevPlanByYear in EmpDevPlanController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<EmpDevPlanReqDto>> response = new ManagerDto<>();
        List<EmpDevPlanReqDto> content = empDevPlanServ.getByUserIdAndYear(id, year);
        response.setContent(content);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success Get data", endTime - startTime));
        Log.info("End getEmpDevPlanByYear in EmpDevPlanController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
