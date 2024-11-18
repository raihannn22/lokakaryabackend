package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;

public interface EmpSuggestionServ {
    
    List<EmpSuggestionDto> getAllEmpSuggestion();
    EmpSuggestionDto getEmpSuggestionById(UUID id);
    EmpSuggestionDto createEmpSuggestion(EmpSuggestionReqDto empSuggestionDto);
    EmpSuggestionDto updateEmpSuggestion(UUID id, EmpSuggestionReqDto empSuggestionReqDto);
    Boolean deleteEmpSuggestion(UUID id);
}
