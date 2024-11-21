package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Config.GetUserUtil;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Entity.AssessmentSummary;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.AssessmentSummaryRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.AssessmentSummaryServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AssessmentSummaryServImpl implements AssessmentSummaryServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AssessmentSummaryRepo assessmentSummaryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<AssessmentSummaryReqDto> getAllAssessmentSummary() {
        List<AssessmentSummary> response = assessmentSummaryRepo.findAll();
        List<AssessmentSummaryReqDto> assessmentSummaryReqDto = new ArrayList<>();
        for (AssessmentSummary assessmentSummary : response) {
            assessmentSummaryReqDto.add(AssessmentSummaryReqDto.fromEntity(assessmentSummary));
        }
        return assessmentSummaryReqDto;
    }

    @Override
    public AssessmentSummaryReqDto getAssessmentSummaryById(UUID id) {
        Log.info("Start getAssessmentSummaryById in AssessmentSummaryServImpl");
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findById(id).orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        Log.info("End getAssessmentSummaryById in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummary);
    }

    @Override
    public AssessmentSummaryReqDto createAssessmentSummary(AssessmentSummaryDto assessmentSummaryDto) {
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Optional<User> user = userRepo.findById(assessmentSummaryDto.getUserId());
        if (user.isPresent()) {
            AssessmentSummary assessmentSummary = assessmentSummaryDto.toEntity(assessmentSummaryDto, user.get(), null, null, currentUserEntity, new java.util.Date());
            assessmentSummaryRepo.save(assessmentSummary);
            return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary));
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start updateAssessmentSummary in AssessmentSummaryServImpl");
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        AssessmentSummary assessmentSummary1 = assessmentSummaryDto.toEntity(assessmentSummaryDto, assessmentSummary.getUser(), currentUserEntity, new java.util.Date(), assessmentSummary.getCreatedBy(), assessmentSummary.getCreatedAt());
        Log.info("End updateAssessmentSummary in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary1));
    }

    @Override
    public Boolean deleteAssessmentSummary(UUID id) {
        Log.info("Start deleteAssessmentSummary in AssessmentSummaryServImpl");
        if (assessmentSummaryRepo.existsById(id)) {
            assessmentSummaryRepo.deleteById(id);
            Log.info("End deleteAssessmentSummary in AssessmentSummaryServImpl");
            return true;
        }
        throw new RuntimeException("AssessmentSummary not found");
    }
}