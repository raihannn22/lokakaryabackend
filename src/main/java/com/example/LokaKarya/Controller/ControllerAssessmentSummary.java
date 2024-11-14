//package com.example.LokaKarya.Controller;
//
//import com.example.LokaKarya.Dto.AssessmentSummaryDto;
//import com.example.LokaKarya.Services.tblAssessmentSummaryServ;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/assessmenetSummary")
//public class ControllerAssessmentSummary {
//    @Autowired
//    private tblAssessmentSummaryServ tblAssessmentSummaryServ;
//
//
//    @PostMapping("/createAssessmentSummary")
//    public AssessmentSummaryDto createAssessmentSummary(AssessmentSummaryDto tblAssessmentSummary) {
//        return tblAssessmentSummaryServ.createAssessmentSummary(tblAssessmentSummary);
//    }
//}
