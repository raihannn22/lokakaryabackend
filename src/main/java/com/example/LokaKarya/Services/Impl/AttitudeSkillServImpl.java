package com.example.LokaKarya.Services.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.AttitudeSkill;
import com.example.LokaKarya.Entity.GroupAttitudeSkill;
import com.example.LokaKarya.Repository.AttitudeSkillRepo;
import com.example.LokaKarya.Repository.GroupAttitudeSkillRepo;
import com.example.LokaKarya.Services.AttitudeSkillServ;

@Service
public class AttitudeSkillServImpl implements AttitudeSkillServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Override
    public List<AttitudeSkillReqDto> getAllAttitudeSkill() {
        List<AttitudeSkill> response = attitudeSkillRepo.findAll();
        List<AttitudeSkillReqDto> attitudeSkillReqDto = new ArrayList<>();
        for (AttitudeSkill attitudeSkill : response) {
            attitudeSkillReqDto.add(AttitudeSkillReqDto.fromEntity(attitudeSkill));
        }
        return attitudeSkillReqDto;
    }

    @Override
    public AttitudeSkillReqDto getAttitudeSkillById(UUID id) {
        Log.info("Start getAttitudeSkillById in AttitudeSkillServImpl");
        AttitudeSkill attitudeSkill = attitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("AttitudeSkill not found"));
        Log.info("End getAttitudeSkillById in AttitudeSkillServImpl");
        return AttitudeSkillReqDto.fromEntity(attitudeSkill);
    }

    @Override
    public AttitudeSkillReqDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto) {
        Optional<GroupAttitudeSkill> user = groupAttitudeSkillRepo.findById(attitudeSkillDto.getGroupId());
        if (user.isPresent()) {
            AttitudeSkill attitudeSkill = attitudeSkillDto.toEntity(attitudeSkillDto, user.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), user.get().getId(), Date.valueOf(LocalDate.now()));
            attitudeSkillRepo.save(attitudeSkill);
            return AttitudeSkillReqDto.fromEntity(attitudeSkillRepo.save(attitudeSkill));
        }else {
            throw new RuntimeException("Attitude Skill not found");
        }
    }

    @Override
    public AttitudeSkillReqDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");

        AttitudeSkill attitudeSkill = attitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AttitudeSkill not found"));
        attitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());     
        attitudeSkill.setGroupAttitudeSkill(groupAttitudeSkillRepo.findById(attitudeSkillDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group AttitudeSkill not found")));
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        attitudeSkill.setUpdatedBy(UUID.randomUUID());
        attitudeSkill.setUpdatedAt(Date.valueOf(LocalDate.now()));
        attitudeSkillRepo.save(attitudeSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return AttitudeSkillReqDto.fromEntity(attitudeSkillRepo.save(attitudeSkill));
    }

    @Override
public Boolean deleteAttitudeSkill(UUID id) {
    Log.info("Start deleteAttitudeSkill in AttitudeSkillServImpl");

    if (attitudeSkillRepo.existsById(id)) {
        attitudeSkillRepo.deleteById(id);  // hanya menghapus AttitudeSkill berdasarkan id
        Log.info("End deleteAttitudeSkill in AttitudeSkillServImpl");
        return true;
    }
    throw new RuntimeException("AttitudeSkill not found");
}
    
}