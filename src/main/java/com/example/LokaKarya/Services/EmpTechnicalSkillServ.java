package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;

public interface EmpTechnicalSkillServ {
    
    List<EmpTechnicalSkillReqDto> getAllEmpTechnicalSkill();
    EmpTechnicalSkillReqDto getEmpTechnicalSkillById(UUID id);
    EmpTechnicalSkillReqDto createEmpTechnicalSkill(EmpTechnicalSkillDto empTechnicalSkillDto);
    EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto);
    Boolean deleteEmpTechnicalSkill(UUID id);
}
