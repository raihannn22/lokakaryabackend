package com.example.LokaKarya.Services.Impl;

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

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssessmentSummaryServImpl implements AssessmentSummaryServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AssessmentSummaryRepo assessmentSummaryRepo;

    @Autowired
    UserRepo userRepo;

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
        Optional<User> user = userRepo.findById(assessmentSummaryDto.getUserId());
        if (user.isPresent()) {
            AssessmentSummary assessmentSummary = assessmentSummaryDto.toEntity(assessmentSummaryDto, user.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), user.get().getId(), Date.valueOf(LocalDate.now()));
            assessmentSummaryRepo.save(assessmentSummary);
            return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary));
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start updateAssessmentSummary in AssessmentSummaryServImpl");

        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        assessmentSummary.setUser(userRepo.findById(assessmentSummaryDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        assessmentSummary.setYear(assessmentSummaryDto.getYear());
        assessmentSummary.setScore(assessmentSummaryDto.getScore());
        assessmentSummary.setStatus(assessmentSummaryDto.getStatus());
        assessmentSummary.setUpdatedBy(UUID.randomUUID());
        assessmentSummary.setUpdatedAt(Date.valueOf(LocalDate.now()));
        assessmentSummaryRepo.save(assessmentSummary);
        Log.info("End updateAssessmentSummary in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary));
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