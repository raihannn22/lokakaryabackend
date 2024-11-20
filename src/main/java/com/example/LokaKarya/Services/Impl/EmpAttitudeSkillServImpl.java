package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.example.LokaKarya.Repository.EmpAttitudeSkillRepo;
import com.example.LokaKarya.Repository.AttitudeSkillRepo;
import com.example.LokaKarya.Services.EmpAttitudeSkillServ;

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

import com.example.LokaKarya.Entity.AttitudeSkill;

@Service
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAttitudeSkillServImpl.class);

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Override
    public List<EmpAttitudeSkillReqDto> getAllEmpAttitudeSkill() {
       Log.info("Start getAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> response = empAttitudeSkillRepo.findAll();
        List<EmpAttitudeSkillReqDto> empAttitudeSkillReqDto = new ArrayList<>();

        for (EmpAttitudeSkill empAttitudeSkill : response) {
            empAttitudeSkillReqDto.add(EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill));
        }
       Log.info("End getAllEmpAttitudeSkillt in EmpAttitudeSkillServImpl");
        return empAttitudeSkillReqDto;
    }

    @Override
    public EmpAttitudeSkillReqDto getEmpAttitudeSkillById(UUID id) {
        Log.info("Start getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Attitude Skill not found"));
        Log.info("End getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill);
    }

    @Override
    public EmpAttitudeSkillReqDto createEmpAttitudeSkill(EmpAttitudeSkillDto empAttitudeSkillDto) {
        Optional<AttitudeSkill> attitudeSkill = attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId());
        if (attitudeSkill.isPresent()) {
            EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillDto.toEntity(empAttitudeSkillDto, attitudeSkill.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), attitudeSkill.get().getId(), Date.valueOf(LocalDate.now()));
           empAttitudeSkillRepo.save(empAttitudeSkill);
            return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkillRepo.save(empAttitudeSkill));
        }else {
            throw new RuntimeException("EmpAttitudeSkill not found");
        }
    }

    @Override
    public EmpAttitudeSkillReqDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillDto) {
        Log.info("Start updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");

        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Attitude Skill not found"));
        empAttitudeSkill.setUserId(empAttitudeSkillDto.getUserId());     
        empAttitudeSkill.setAttitudeSkill(attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId()).orElseThrow(() -> new RuntimeException("Attitude Skill not found")));
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        empAttitudeSkillRepo.save(empAttitudeSkill);
        Log.info("End updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkillRepo.save(empAttitudeSkill));
    }

    @Override
        public Boolean deleteEmpAttitudeSkill(UUID id) {
            Log.info("Start deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");

            if (empAttitudeSkillRepo.existsById(id)) {
                empAttitudeSkillRepo.deleteById(id);  // hanya menghapus Attitude berdasarkan id
                Log.info("End deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpAttitudeSkill not found");
        }

    // private void updateEmpAttitudeSkillFields(EmpAttitudeSkill existingEmpAttitudeSkill, EmpAttitudeSkillReqDto empAttitudeSkillDto) {
    //     if (empAttitudeSkillDto.getUserId() != null) {
    //         existingEmpAttitudeSkill.setUserId(empAttitudeSkillDto.getUserId());
    //     }
    //     if (empAttitudeSkillDto.getAttitudeSkillId() != null) {
    //         existingEmpAttitudeSkill.setAttitudeSkillId(empAttitudeSkillDto.getAttitudeSkillId());
    //     }
    //     if (empAttitudeSkillDto.getScore() != null) {
    //         existingEmpAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
    //     }
    //     if (empAttitudeSkillDto.getAssessmentYear() != null) {
    //         existingEmpAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
    //     }
    // }
}
