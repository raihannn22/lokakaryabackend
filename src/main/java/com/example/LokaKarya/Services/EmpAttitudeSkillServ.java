package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpAttitudeSkillServ {
    List<EmpAttitudeSkillReqDto> getAllEmpAttitudeSkill();
    EmpAttitudeSkillReqDto getEmpAttitudeSkillById(UUID id);
    EmpAttitudeSkillReqDto createEmpAttitudeSkill(EmpAttitudeSkillDto empAttitudeSkillDto);
    EmpAttitudeSkillReqDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillReqDto);
    Boolean deleteEmpAttitudeSkill(UUID id);
}

