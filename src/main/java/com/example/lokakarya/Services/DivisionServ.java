package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.Division.DivisionDto;
import com.example.lokakarya.Dto.Division.DivisionReqDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;

public interface DivisionServ {
    List<DivisionReqDto> getAllDivision();
    DivisionReqDto getDivisionById(UUID id);
    DivisionReqDto createDivision(DivisionDto divisionDto);
    DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto);
    Boolean deleteDivision(UUID id);

    List<DivisionReqDto> getPaginatedDivision(int page, int size, String sort, String direction, String searchKeyword); 
    long count();
    long countBySearchKeyword(String searchKeyword);
}
