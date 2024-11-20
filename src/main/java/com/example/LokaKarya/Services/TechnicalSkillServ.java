package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;

public interface TechnicalSkillServ {
    List<TechnicalSkillDto> getAllTechnicalSkill();
    TechnicalSkillDto getTechnicalSkillById(UUID id);
    TechnicalSkillDto createTechnicalSkill(TechnicalSkillReqDto technicalSkillDto);
    TechnicalSkillDto updateTechnicalSkill(UUID id, TechnicalSkillReqDto technicalSkillDto);
    Boolean deleteTechnicalSkill(UUID id);
}

