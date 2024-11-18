package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;

public interface EmpTechnicalSkillServ {
    
    List<EmpTechnicalSkillDto> getAllEmpTechnicalSkill();
    EmpTechnicalSkillDto getEmpTechnicalSkillById(UUID id);
    EmpTechnicalSkillDto createEmpTechnicalSkill(EmpTechnicalSkillReqDto empTechnicalSkillDto);
    EmpTechnicalSkillDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillReqDto empTechnicalSkillReqDto);
    Boolean deleteEmpTechnicalSkill(UUID id);
}
