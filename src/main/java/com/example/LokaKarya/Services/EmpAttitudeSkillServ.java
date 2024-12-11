package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;

public interface EmpAttitudeSkillServ {
    List<EmpAttitudeSkillReqDto> getAllEmpAttitudeSkill();
    EmpAttitudeSkillReqDto getEmpAttitudeSkillById(UUID id);
    EmpAttitudeSkillReqDto createEmpAttitudeSkill(EmpAttitudeSkillDto empAttitudeSkillDto);
    List<EmpAttitudeSkillReqDto> createAllEmpAttitudeSkill(List<EmpAttitudeSkillDto> empAttitudeSkillDtos);
    List<EmpAttitudeSkillReqDto> getEmpAttitudeSkillByUserId(UUID userId);
    List<EmpAttitudeSkillReqDto> getEmpAttitudeSkillsByUserIdAndYear(UUID userId, Integer assessmentYear);

    // EmpAttitudeSkillReqDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillReqDto);
    // Boolean deleteEmpAttitudeSkill(UUID id);


    
}

