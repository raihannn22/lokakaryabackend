package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface AttitudeSkillServ {
    List<AttitudeSkillDto> getAllAttitudeSkill();
    AttitudeSkillDto getAttitudeSkillById(UUID id);
    AttitudeSkillDto createAttitudeSkill(AttitudeSkillReqDto attitudeSkillDto);
    AttitudeSkillDto updateAttitudeSkill(UUID id, AttitudeSkillReqDto attitudeSkillDto);
    Boolean deleteAttitudeSkill(UUID id);
}

