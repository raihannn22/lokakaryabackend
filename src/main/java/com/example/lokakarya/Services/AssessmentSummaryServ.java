package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.AssessmentSummary.TotalScoreDto;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryReqDto> getAllAssessmentSummary();
    AssessmentSummaryReqDto getAssessmentSummaryById(UUID id);
    AssessmentSummaryReqDto createAssessmentSummary(AssessmentSummaryDto assessmentSummaryDto);
    AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto);
    Boolean deleteAssessmentSummary(UUID id);
    List<TotalScoreDto> calculateTotalScoresForAllUsers(int year);
    void calculateAndSaveScoreForUser(UUID userId, int year);
    List<AssessmentSummaryReqDto> getAllAssessmentSummaryByYear(int year);
    AssessmentSummaryReqDto setAssessmentSummary1(UUID id, int year);
    AssessmentSummaryReqDto setAssessmentSummary0(UUID id, int year);
    AssessmentSummaryReqDto getAssessmentSummaryByUserIdAndYear(UUID id, int year);
}