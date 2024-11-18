package com.example.LokaKarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Entity.AttitudeSkill;
import com.example.LokaKarya.Repository.AttitudeSkillRepo;
import com.example.LokaKarya.Services.AttitudeSkillServ;

@Service
public class AttitudeSkillServImpl implements AttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAttitudeSkillServImpl.class);

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Override
    public List<AttitudeSkillDto> getAllAttitudeSkill() {
       Log.info("Start getAllpAttitudeSkill in AttitudeSkillServImpl");
        List<AttitudeSkill> response = attitudeSkillRepo.findAll();
        List<AttitudeSkillDto> attitudeSkillList = new ArrayList<>();

        for (AttitudeSkill attitudeSkill : response) {
            AttitudeSkillDto attitudeSkillDto = AttitudeSkillDto.fromEntity(attitudeSkill);
            attitudeSkillList.add(attitudeSkillDto);
        }
       Log.info("End getAllAttitudeSkill in AttitudeSkillServImpl");
        return attitudeSkillList;
    }

    @Override
    public AttitudeSkillDto getAttitudeSkillById(UUID id) {
       Log.info("Start getAttitudeSkillById in AttitudeSkillServImpl");
        Optional<AttitudeSkill> attitudeSkill = attitudeSkillRepo.findById(id);
       Log.info("End getAchievementById in AchievementServImpl");
        return attitudeSkill.map(AttitudeSkillDto::fromEntity).orElse(null);
    }

    @Override
    public AttitudeSkillDto createAttitudeSkill(AttitudeSkillReqDto attitudeSkillDto) {
       Log.info("Start createAttitudeSkill in AttitudeSkillServImpl");

        AttitudeSkill attitudeSkill = AttitudeSkillReqDto.toEntity(attitudeSkillDto);

        attitudeSkillRepo.save(attitudeSkill);
       Log.info("End createAttitudeSkill in AttitudeSkillServImpl");
        return AttitudeSkillDto.fromEntity(attitudeSkill);
    }

    @Override
    public AttitudeSkillDto updateAttitudeSkill (UUID id, AttitudeSkillReqDto attitudeSkillDto) {
       Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");
        AttitudeSkill findAttitudeSkill  = attitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Attitude Skill not found"));

        updateAttitudeSkillFields(findAttitudeSkill , attitudeSkillDto);

        attitudeSkillRepo.save(findAttitudeSkill);
       Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return AttitudeSkillDto.fromEntity(findAttitudeSkill);
    }

    @Override
    public Boolean deleteAttitudeSkill(UUID id) {
       Log.info("Start deleteAttitudeSkill in AttitudeSkillServImpl");
        AttitudeSkill findAttitudeSkill = attitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Attitude Skill not found"));
        attitudeSkillRepo.delete(findAttitudeSkill);
       Log.info("End deleteAttitudeSkill in AttitudeSkillServImpl");
        return true;
    }


    private void updateAttitudeSkillFields(AttitudeSkill existingAttitudeSkill, AttitudeSkillReqDto attitudeSkillDto) {
        if (attitudeSkillDto.getAttitudeSkill() != null) {
            existingAttitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());
        }
        if (attitudeSkillDto.getGroupId() != null) {
            existingAttitudeSkill.setGroupId(attitudeSkillDto.getGroupId());
        }
        if (attitudeSkillDto.getEnabled() != null) {
            existingAttitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        }
        

    }
}
