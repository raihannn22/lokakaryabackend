package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.example.LokaKarya.Repository.EmpAchievementSkillRepo;
import com.example.LokaKarya.Services.EmpAchievementSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpAchievementSkillServImpl implements EmpAchievementSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAchievementSkillServImpl.class);

    @Autowired
    private EmpAchievementSkillRepo empAchievementSkillRepo;

    @Override
    public List<EmpAchievementSkillDto> getAllEmpAchievementSkill() {
       Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findAll();
        List<EmpAchievementSkillDto> empAchievementSkillList = new ArrayList<>();

        for (EmpAchievementSkill empAchievementSkill : response) {
            EmpAchievementSkillDto empAchievementSkillDto = EmpAchievementSkillDto.fromEntity(empAchievementSkill);
            empAchievementSkillList.add(empAchievementSkillDto);
        }
       Log.info("End getAllEmpAchievementSkillt in EmpAchievementSkillServImpl");
        return empAchievementSkillList;
    }

    @Override
    public EmpAchievementSkillDto getEmpAchievementSkillById(UUID id) {
       Log.info("Start getEmpAchievementSkillById in EmpAchievementSkillServImpl");
        Optional<EmpAchievementSkill> empAchievementSkill = empAchievementSkillRepo.findById(id);
       Log.info("End getEmpAchievementSkillById in EmpAchievementSkillServImpl");
        return empAchievementSkill.map(EmpAchievementSkillDto::fromEntity).orElse(null);
    }

    @Override
    public EmpAchievementSkillDto createEmpAchievementSkill (EmpAchievementSkillReqDto empAchievementSkilltDto) {
       Log.info("Start createGroupAchievement in GroupAchievementServImpl");

        EmpAchievementSkill empAchievementSkill = EmpAchievementSkillReqDto.toEntity(empAchievementSkilltDto);

        empAchievementSkillRepo.save(empAchievementSkill);
       Log.info("End createGroupAchievement in GroupAchievementServImpl");
        return EmpAchievementSkillDto.fromEntity(empAchievementSkill);
    }

    @Override
    public EmpAchievementSkillDto updateEmpAchievementSkill (UUID id, EmpAchievementSkillReqDto empAchievementSkillDto) {
       Log.info("Start updateEmpAchievementSkill in EmpAchievementSkillServImpl");
        EmpAchievementSkill findEmpAchievementSkill  = empAchievementSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));

        updateEmpAchievementSkillFields(findEmpAchievementSkill , empAchievementSkillDto);

        empAchievementSkillRepo.save(findEmpAchievementSkill);
       Log.info("End updateEmpAchievementSkill in EmpAchievementSkillServImpl");
        return EmpAchievementSkillDto.fromEntity(findEmpAchievementSkill);
    }

    @Override
    public Boolean deleteEmpAchievementSkill(UUID id) {
       Log.info("Start deleteEmpAchievementSkill in EmpAchievementSkillServImpl");
        EmpAchievementSkill findEmpAchievementSkill = empAchievementSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));
        empAchievementSkillRepo.delete(findEmpAchievementSkill);
       Log.info("End deleteEmpAchievementSkill in EmpAchievementSkillServImpl");
        return true;
    }

    private void updateEmpAchievementSkillFields(EmpAchievementSkill existingEmpAchievementSkill, EmpAchievementSkillReqDto empAchievementSkillDto) {
        if (empAchievementSkillDto.getUserId() != null) {
            existingEmpAchievementSkill.setUserId(empAchievementSkillDto.getUserId());
        }
        if (empAchievementSkillDto.getNotes() != null) {
            existingEmpAchievementSkill.setNotes(empAchievementSkillDto.getNotes());
        }
        if (empAchievementSkillDto.getAchievementId() != null) {
            existingEmpAchievementSkill.setAchievementId(empAchievementSkillDto.getAchievementId());
        }
        if (empAchievementSkillDto.getScore() != null) {
            existingEmpAchievementSkill.setScore(empAchievementSkillDto.getScore());
        }
        if (empAchievementSkillDto.getAssessmentYear() != null) {
            existingEmpAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
        }
        
        

    }
}
