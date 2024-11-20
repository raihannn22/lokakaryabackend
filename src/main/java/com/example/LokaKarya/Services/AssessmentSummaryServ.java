package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;

import java.util.List;
import java.util.UUID;

public interface AssessmentSummaryServ {
    List<AssessmentSummaryReqDto> getAllAssessmentSummary();
    AssessmentSummaryReqDto getAssessmentSummaryById(UUID id);
    AssessmentSummaryReqDto createAssessmentSummary(AssessmentSummaryDto assessmentSummaryDto);
    AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto);
    Boolean deleteAssessmentSummary(UUID id);
}
