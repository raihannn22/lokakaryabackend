package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpAttitudeSkillServ {
    List<EmpAttitudeSkillDto> getAllEmpAttitudeSkill();
    EmpAttitudeSkillDto getEmpAttitudeSkillById(UUID id);
    EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillReqDto empAttitudeSkillDto);
    EmpAttitudeSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillReqDto empAttitudeSkillReqDto);
    Boolean deleteEmpAttitudeSkill(UUID id);
}

