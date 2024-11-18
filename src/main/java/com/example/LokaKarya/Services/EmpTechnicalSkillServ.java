package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpTechnicalSkillServ {
    List<EmpTechnicalSkillDto> getAllEmpAttitudeSkill();

    EmpTechnicalSkillDto getEmpAttitudeSkillById(UUID id);

    EmpTechnicalSkillDto createEmpAttitudeSkill(EmpAttitudeSkillReqDto empAttitudeSkillDto);

    EmpTechnicalSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillReqDto empAttitudeSkillReqDto);

    Boolean deleteEmpAttitudeSkill(UUID id);
}
