package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;

public interface EmpAchievementSkillServ {
    List<EmpAchievementSkillDto> getAllEmpAchievementSkill();
    EmpAchievementSkillDto getEmpAchievementSkillById(UUID id);
    EmpAchievementSkillDto createEmpAchievementSkill(EmpAchievementSkillReqDto empAchievementSkillDto);
    EmpAchievementSkillDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillReqDto empAchievementSkillReqDto);
    Boolean deleteEmpAchievementSkill(UUID id);
}

