package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.Division.DivisionDto;
import com.example.lokakarya.Dto.Division.DivisionReqDto;

import java.util.List;
import java.util.UUID;

public interface DivisionServ {
    List<DivisionReqDto> getAllDivision();
    DivisionReqDto getDivisionById(UUID id);
    DivisionReqDto createDivision(DivisionDto divisionDto);
    DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto);
    Boolean deleteDivision(UUID id);
}
