package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;

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
