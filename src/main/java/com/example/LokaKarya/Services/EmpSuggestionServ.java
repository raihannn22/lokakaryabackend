package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;

public interface EmpSuggestionServ {
    
    List<EmpSuggestionReqDto> getAllEmpSuggestion();
    EmpSuggestionReqDto getEmpSuggestionById(UUID id);
    EmpSuggestionReqDto createEmpSuggestion(EmpSuggestionDto empSuggestionDto);
    EmpSuggestionReqDto updateEmpSuggestion(UUID id, EmpSuggestionDto empSuggestionDto);
    Boolean deleteEmpSuggestion(UUID id);
}
