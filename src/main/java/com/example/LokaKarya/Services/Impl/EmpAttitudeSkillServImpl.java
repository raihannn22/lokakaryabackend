package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.example.LokaKarya.Repository.EmpAttitudeSkillRepo;
import com.example.LokaKarya.Services.EmpAttitudeSkillServ;

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
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAttitudeSkillServImpl.class);

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Override
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkill() {
       Log.info("Start getAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> response = empAttitudeSkillRepo.findAll();
        List<EmpAttitudeSkillDto> empAttitudeSkillList = new ArrayList<>();

        for (EmpAttitudeSkill empAttitudeSkill : response) {
            EmpAttitudeSkillDto empAttitudeSkillDto = EmpAttitudeSkillDto.fromEntity(empAttitudeSkill);
            empAttitudeSkillList.add(empAttitudeSkillDto);
        }
       Log.info("End getAllEmpAttitudeSkillt in EmpAttitudeSkillServImpl");
        return empAttitudeSkillList;
    }

    @Override
    public EmpAttitudeSkillDto getEmpAttitudeSkillById(UUID id) {
       Log.info("Start getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        Optional<EmpAttitudeSkill> empAttitudeSkill = empAttitudeSkillRepo.findById(id);
       Log.info("End getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        return empAttitudeSkill.map(EmpAttitudeSkillDto::fromEntity).orElse(null);
    }

    @Override
    public EmpAttitudeSkillDto createEmpAttitudeSkill (EmpAttitudeSkillReqDto empAttitudeSkilltDto) {
       Log.info("Start createGroupAchievement in GroupAchievementServImpl");

        EmpAttitudeSkill empAttitudeSkill = EmpAttitudeSkillReqDto.toEntity(empAttitudeSkilltDto);

        empAttitudeSkillRepo.save(empAttitudeSkill);
       Log.info("End createGroupAchievement in GroupAchievementServImpl");
        return EmpAttitudeSkillDto.fromEntity(empAttitudeSkill);
    }

    @Override
    public EmpAttitudeSkillDto updateEmpAttitudeSkill (UUID id, EmpAttitudeSkillReqDto empAttitudeSkillDto) {
       Log.info("Start updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        EmpAttitudeSkill findEmpAttitudeSkill  = empAttitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));

        updateEmpAttitudeSkillFields(findEmpAttitudeSkill , empAttitudeSkillDto);

        empAttitudeSkillRepo.save(findEmpAttitudeSkill);
       Log.info("End updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillDto.fromEntity(findEmpAttitudeSkill);
    }

    @Override
    public Boolean deleteEmpAttitudeSkill(UUID id) {
       Log.info("Start deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        EmpAttitudeSkill findEmpAttitudeSkill = empAttitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));
        empAttitudeSkillRepo.delete(findEmpAttitudeSkill);
       Log.info("End deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return true;
    }

    private void updateEmpAttitudeSkillFields(EmpAttitudeSkill existingEmpAttitudeSkill, EmpAttitudeSkillReqDto empAttitudeSkillDto) {
        if (empAttitudeSkillDto.getUserId() != null) {
            existingEmpAttitudeSkill.setUserId(empAttitudeSkillDto.getUserId());
        }
        if (empAttitudeSkillDto.getAttitudeSkillId() != null) {
            existingEmpAttitudeSkill.setAttitudeSkillId(empAttitudeSkillDto.getAttitudeSkillId());
        }
        if (empAttitudeSkillDto.getScore() != null) {
            existingEmpAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        }
        if (empAttitudeSkillDto.getAssessmentYear() != null) {
            existingEmpAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        }
        
        

    }
}
