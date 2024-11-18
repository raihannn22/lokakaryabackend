package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Repository.EmpTechnicalSkillRepo;
import com.example.LokaKarya.Services.EmpTechnicalSkillServ;

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
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillServImpl.class);

    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Override
    public List<EmpTechnicalSkillDto> getAllEmpTechnicalSkill() {
       Log.info("Start getAllEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        List<EmpTechnicalSkill> response = empTechnicalSkillRepo.findAll();
        List<EmpTechnicalSkillDto> empTechnicalSkillList = new ArrayList<>();

        for (EmpTechnicalSkill empTechnicalSkill : response) {
            EmpTechnicalSkillDto empTechnicalSkillDto = EmpTechnicalSkillDto.fromEntity(empTechnicalSkill);
            empTechnicalSkillList.add(empTechnicalSkillDto);
        }
       Log.info("End getAllEmpTechnicalSkillt in EmpTechnicalSkillServImpl");
        return empTechnicalSkillList;
    }

    @Override
    public EmpTechnicalSkillDto getEmpTechnicalSkillById(UUID id) {
       Log.info("Start getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        Optional<EmpTechnicalSkill> empTechnicalSkill = empTechnicalSkillRepo.findById(id);
       Log.info("End getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        return empTechnicalSkill.map(EmpTechnicalSkillDto::fromEntity).orElse(null);
    }

    @Override
    public EmpTechnicalSkillDto createEmpTechnicalSkill (EmpTechnicalSkillReqDto empTechnicalSkilltDto) {
       Log.info("Start createGroupAchievement in GroupAchievementServImpl");

        EmpTechnicalSkill empTechnicalSkill = EmpTechnicalSkillReqDto.toEntity(empTechnicalSkilltDto);

        empTechnicalSkillRepo.save(empTechnicalSkill);
       Log.info("End createGroupAchievement in GroupAchievementServImpl");
        return EmpTechnicalSkillDto.fromEntity(empTechnicalSkill);
    }

    @Override
    public EmpTechnicalSkillDto updateEmpTechnicalSkill (UUID id, EmpTechnicalSkillReqDto empTechnicalSkillDto) {
       Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        EmpTechnicalSkill findEmpTechnicalSkill  = empTechnicalSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Technical Skill"));

        updateEmpTechnicalSkillFields(findEmpTechnicalSkill , empTechnicalSkillDto);

        empTechnicalSkillRepo.save(findEmpTechnicalSkill);
       Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillDto.fromEntity(findEmpTechnicalSkill);
    }

    @Override
    public Boolean deleteEmpTechnicalSkill(UUID id) {
       Log.info("Start deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        EmpTechnicalSkill findEmpTechnicalSkill = empTechnicalSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));
        empTechnicalSkillRepo.delete(findEmpTechnicalSkill);
       Log.info("End deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        return true;
    }

    private void updateEmpTechnicalSkillFields(EmpTechnicalSkill existingEmpTechnicalSkill, EmpTechnicalSkillReqDto empTechnicalSkillDto) {
        if (empTechnicalSkillDto.getUserId() != null) {
            existingEmpTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());
        }
        if (empTechnicalSkillDto.getTechnicalSkillId() != null) {
            existingEmpTechnicalSkill.setTechnicalSkillId(empTechnicalSkillDto.getTechnicalSkillId());
        }
        if (empTechnicalSkillDto.getScore() != null) {
            existingEmpTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        }
        if (empTechnicalSkillDto.getAssessmentYear() != null) {
            existingEmpTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        }
        
        

    }
}
