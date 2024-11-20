package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface AttitudeSkillServ {
    List<AttitudeSkillReqDto> getAllAttitudeSkill();
    AttitudeSkillReqDto getAttitudeSkillById(UUID id);
    AttitudeSkillReqDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto);
    AttitudeSkillReqDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto);
    Boolean deleteAttitudeSkill(UUID id);
}

