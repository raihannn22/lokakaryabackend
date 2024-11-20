package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Entity.GroupAchievement;
import com.example.LokaKarya.Repository.EmpTechnicalSkillRepo;
import com.example.LokaKarya.Repository.TechnicalSkillRepo;
import com.example.LokaKarya.Services.EmpTechnicalSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillServImpl.class);

    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Override
    public List<EmpTechnicalSkillReqDto> getAllEmpTechnicalSkill() {
       Log.info("Start getAllEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        List<EmpTechnicalSkill> response = empTechnicalSkillRepo.findAll();
        List<EmpTechnicalSkillReqDto> empTechnicalSkillReqDto = new ArrayList<>();

        for (EmpTechnicalSkill empTechnicalSkill : response) {
            empTechnicalSkillReqDto.add(EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkill));
        }
       Log.info("End getAllEmpTechnicalSkillt in EmpTechnicalSkillServImpl");
        return empTechnicalSkillReqDto;
    }

    @Override
    public EmpTechnicalSkillReqDto getEmpTechnicalSkillById(UUID id) {
        Log.info("Start getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
        Log.info("End getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkill);
    }

    @Override
    public EmpTechnicalSkillReqDto createEmpTechnicalSkill(EmpTechnicalSkillDto empTechnicalSkillDto) {
        Optional<TechnicalSkill> technicalSkill = technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId());
        if (technicalSkill.isPresent()) {
            EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillDto.toEntity(empTechnicalSkillDto, technicalSkill.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), technicalSkill.get().getId(), Date.valueOf(LocalDate.now()));
           empTechnicalSkillRepo.save(empTechnicalSkill);
            return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkillRepo.save(empTechnicalSkill));
        }else {
            throw new RuntimeException("EmpTechnicalSkill not found");
        }
    }

    @Override
    public EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto) {
        Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");

        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
        empTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());     
        empTechnicalSkill.setTechnicalSkill(technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId()).orElseThrow(() -> new RuntimeException("Technical Skill not found")));
        empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        empTechnicalSkillRepo.save(empTechnicalSkill);
        Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkillRepo.save(empTechnicalSkill));
    }

        @Override
        public Boolean deleteEmpTechnicalSkill(UUID id) {
            Log.info("Start deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");

            if (empTechnicalSkillRepo.existsById(id)) {
                empTechnicalSkillRepo.deleteById(id);  // hanya menghapus achievement berdasarkan id
                Log.info("End deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpTechnicalSkill not found");
        }

    // private void updateEmpTechnicalSkillFields(EmpTechnicalSkill existingEmpTechnicalSkill, EmpTechnicalSkillReqDto empTechnicalSkillDto) {
    //     if (empTechnicalSkillDto.getUserId() != null) {
    //         existingEmpTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());
    //     }
    //     if (empTechnicalSkillDto.getTechnicalSkillId() != null) {
    //         existingEmpTechnicalSkill.setTechnicalSkillId(empTechnicalSkillDto.getTechnicalSkillId());
    //     }
    //     if (empTechnicalSkillDto.getScore() != null) {
    //         existingEmpTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
    //     }
    //     if (empTechnicalSkillDto.getAssessmentYear() != null) {
    //         existingEmpTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
    //     }
        
        

    // }
}
