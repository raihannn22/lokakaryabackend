package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.Dto.Division.DivisionDto;
import com.example.LokaKarya.Dto.Division.DivisionReqDto;

import java.util.List;
import java.util.UUID;

public interface DivisionServ {
    List<DivisionReqDto> getAllDivision();
    DivisionReqDto getDivisionById(UUID id);
    DivisionReqDto createDivision(DivisionDto divisionDto);
    DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto);
    Boolean deleteDivision(UUID id);
}
