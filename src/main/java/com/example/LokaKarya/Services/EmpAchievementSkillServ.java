package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpAchievementSkillServ {
    List<EmpAchievementSkillDto> getAllEmpAchievementSkill();
    EmpAchievementSkillDto getEmpAchievementSkillById(UUID id);
    EmpAchievementSkillDto createEmpAchievementSkill(EmpAchievementSkillReqDto empAchievementSkillDto);
    EmpAchievementSkillDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillReqDto empAchievementSkillReqDto);
    Boolean deleteEmpAchievementSkill(UUID id);
}

