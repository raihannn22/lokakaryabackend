package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;

public interface EmpTechnicalSkillServ {
    List<EmpTechnicalSkillReqDto> getAllEmpTechnicalSkill();
    EmpTechnicalSkillReqDto getEmpTechnicalSkillById(UUID id);
    EmpTechnicalSkillReqDto createEmpTechnicalSkill(EmpTechnicalSkillDto empTechnicalSkillDto);
    List<EmpTechnicalSkillReqDto> createAllEmpTechnicalSkill(List<EmpTechnicalSkillDto> empTechnicalSkillDto);
    List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillByUserId(UUID userId);
    List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillsByUserIdAndYear(UUID userId, Integer assessmentYear);
    EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto);
    Boolean deleteEmpTechnicalSkill(UUID id);

}
