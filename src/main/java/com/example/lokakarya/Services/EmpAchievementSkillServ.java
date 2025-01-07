package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.lokakarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;

public interface EmpAchievementSkillServ {
    List<EmpAchievementSkillReqDto> getAllEmpAchievementSkill();
    EmpAchievementSkillReqDto getEmpAchievementSkillById(UUID id);
    EmpAchievementSkillReqDto createEmpAchievementSkill(EmpAchievementSkillDto empAchievementSkillDto);
    List<EmpAchievementSkillReqDto> createAllEmpAchievementSkill(List<EmpAchievementSkillDto> empAchievementSkillDtos);
    EmpAchievementSkillReqDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillDto empAchievementSkillDto);
    Boolean deleteEmpAchievementSkill(UUID id);
    List<EmpAchievementSkillReqDto> getAllEmpAchievementSkillByUser(UUID id);
    List<EmpAchievementSkillReqDto> getAllEmpAchievementSkillByUserAndYear(UUID id, Integer year);
    List<EmpAchievementSkillReqDto> getEmpAchievementSkillByYear(Integer year);
}