package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionReqDto;

public interface EmpSuggestionServ {
    
    List<EmpSuggestionReqDto> getAllEmpSuggestion();
    EmpSuggestionReqDto getEmpSuggestionById(UUID id);
    List<EmpSuggestionReqDto> getEmpSuggestionByUserId(UUID userId);
    EmpSuggestionReqDto createEmpSuggestion(EmpSuggestionDto empSuggestionDto);
    List<EmpSuggestionReqDto> createAllEmpSuggestion(List<EmpSuggestionDto> empSuggestionDto);
    EmpSuggestionReqDto updateEmpSuggestion(UUID id, EmpSuggestionDto empSuggestionDto);
    Boolean deleteEmpSuggestion(UUID id);
    
    List<EmpSuggestionReqDto> getEmpSuggestionsByUserIdAndYear(UUID userId, Integer assessmentYear);
}
