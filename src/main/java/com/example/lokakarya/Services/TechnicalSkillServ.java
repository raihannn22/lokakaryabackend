package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface TechnicalSkillServ {
    List<TechnicalSkillReqDto> getAllTechnicalSkill();
    TechnicalSkillReqDto getTechnicalSkillById(UUID id);
    TechnicalSkillReqDto createTechnicalSkill(TechnicalSkillDto TechnicalSkillDto);
    TechnicalSkillReqDto updateTechnicalSkill(UUID id, TechnicalSkillDto TechnicalSkillDto);
    Boolean deleteTechnicalSkill(UUID id);
}

