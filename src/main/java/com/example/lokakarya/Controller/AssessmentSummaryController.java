package com.example.lokakarya.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.AssessmentSummary.TotalScoreDto;
import com.example.lokakarya.Services.AssessmentSummaryServ;
import com.example.lokakarya.util.ServerResponseList;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assessmentSummary")
public class AssessmentSummaryController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryController.class);

    @Autowired
    AssessmentSummaryServ assessmentSummaryServ;

    @GetMapping("/get/all")
    public ResponseEntity<ManagerDto<List<AssessmentSummaryReqDto>>> getAllAssessmentSummary() {
        Log.info("Start getAllAssessmentSummary in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AssessmentSummaryReqDto>> response = new ManagerDto<>();
        List<AssessmentSummaryReqDto> content = assessmentSummaryServ.getAllAssessmentSummary();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAssessmentSummary in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> getAssessmentSummaryDetail(@PathVariable("id") UUID id) {
        Log.info("Start getAssessmentSummaryDetail in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.getAssessmentSummaryById(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAssessmentSummaryDetail in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> saveAssessmentSummary (@RequestBody AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start saveAssessmentSummary in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.createAssessmentSummary(assessmentSummaryDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAssessmentSummary in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> updateAssessmentSummary(@PathVariable("id") UUID id, @RequestBody AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start updateAssessmentSummary in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.updateAssessmentSummary(id, assessmentSummaryDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAssessmentSummary in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ManagerDto<Boolean>> deleteAssessmentSummary(@RequestParam("id") UUID id) {
        Log.info("Start deleteAssessmentSummary in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = assessmentSummaryServ.deleteAssessmentSummary(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteAssessmentSummary in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/total/{year}")
    public ResponseEntity<List<TotalScoreDto>> getTotalScoresForAllUsers(@PathVariable("year") int year) {
        Log.info("Start getTotalScoresForAllUsers in AssessmentSummaryController");
        List<TotalScoreDto> totalScores = assessmentSummaryServ.calculateTotalScoresForAllUsers(year);
        Log.info("End getTotalScoresForAllUsers in AssessmentSummaryController");
        return ResponseEntity.ok(totalScores);
    }

    @GetMapping("get/all/year/{assessmentYear}")
    public ResponseEntity<ManagerDto<List<AssessmentSummaryReqDto>>> getAllAssessmentSummaryByYear(@PathVariable("assessmentYear") int assessmentYear) {
        Log.info("Start getAllAssessmentSummaryByYear in AssessmentSummaryController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AssessmentSummaryReqDto>> response = new ManagerDto<>();
        List<AssessmentSummaryReqDto> content = assessmentSummaryServ.getAllAssessmentSummaryByYear(assessmentYear);
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAssessmentSummaryByYear in AssessmentSummaryController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{userId}/assessment/{year}/set/1")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> setAssessmentSummaryTo1(@PathVariable ("userId") UUID id, @PathVariable("year") int year){
        Log.info("Starting ");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.setAssessmentSummary1(id, year);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data",executionTime ));
        Log.info(("End"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{userId}/assessment/{year}/set/0")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> setAssessmentSummaryTo0(@PathVariable ("userId") UUID id, @PathVariable("year") int year){
        Log.info("Starting ");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.setAssessmentSummary0(id, year);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data",executionTime ));
        Log.info(("End"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}/assessment/{year}")
    public ResponseEntity<ManagerDto<AssessmentSummaryReqDto>> getAssessmentSummaryByUserId(@PathVariable ("userId") UUID id, @PathVariable("year") int year){
        Log.info("Starting ");
        long startTime = System.currentTimeMillis();
        ManagerDto<AssessmentSummaryReqDto> response = new ManagerDto<>();
        AssessmentSummaryReqDto content = assessmentSummaryServ.getAssessmentSummaryByUserIdAndYear(id, year);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data",executionTime ));
        Log.info(("End"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}