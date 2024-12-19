package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;

public interface TechnicalSkillServ {
    List<TechnicalSkillReqDto> getAllTechnicalSkill();
    TechnicalSkillReqDto getTechnicalSkillById(UUID id);
    TechnicalSkillReqDto createTechnicalSkill(TechnicalSkillDto TechnicalSkillDto);
    TechnicalSkillReqDto updateTechnicalSkill(UUID id, TechnicalSkillDto TechnicalSkillDto);
    Boolean deleteTechnicalSkill(UUID id);
}

