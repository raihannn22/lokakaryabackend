package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.AssessmentSummary.TotalScoreDto;

import java.util.List;
import java.util.UUID;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryReqDto> getAllAssessmentSummary();
    AssessmentSummaryReqDto getAssessmentSummaryById(UUID id);
    AssessmentSummaryReqDto createAssessmentSummary(AssessmentSummaryDto assessmentSummaryDto);
    AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto);
    Boolean deleteAssessmentSummary(UUID id);
    List<TotalScoreDto> calculateTotalScoresForAllUsers(int year);
}