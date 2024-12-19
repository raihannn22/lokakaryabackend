package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.Division.DivisionDto;
import com.example.lokakarya.Dto.Division.DivisionReqDto;

public interface DivisionServ {
    List<DivisionReqDto> getAllDivision();
    DivisionReqDto getDivisionById(UUID id);
    DivisionReqDto createDivision(DivisionDto divisionDto);
    DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto);
    Boolean deleteDivision(UUID id);
}
