package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;

public interface EmpAchievementSkillServ {
    List<EmpAchievementSkillReqDto> getAllEmpAchievementSkill();
    EmpAchievementSkillReqDto getEmpAchievementSkillById(UUID id);
    EmpAchievementSkillReqDto createEmpAchievementSkill(EmpAchievementSkillDto empAchievementSkillDto);
    List<EmpAchievementSkillReqDto> createAllEmpAchievementSkill(List<EmpAchievementSkillDto> empAchievementSkillDtos);

    EmpAchievementSkillReqDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillDto empAchievementSkillDto);
    Boolean deleteEmpAchievementSkill(UUID id);

    List<EmpAchievementSkillReqDto> getAllEmpAchievementSkillByUser(UUID id);
}

