package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillReqDto;

public interface AttitudeSkillServ {
    List<AttitudeSkillReqDto> getAllAttitudeSkill();
    AttitudeSkillReqDto getAttitudeSkillById(UUID id);
    AttitudeSkillReqDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto);
    AttitudeSkillReqDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto);
    Boolean deleteAttitudeSkill(UUID id);
    List<AttitudeSkillReqDto> getAllAttitudeSkillEnabled();
    List<AttitudeSkillReqDto> getPaginatedAttitudeSkill(int page, int size, String sort, String direction, String searchKeyword);
    long count();
    long countBySearchKeyword(String searchKeyword);
}