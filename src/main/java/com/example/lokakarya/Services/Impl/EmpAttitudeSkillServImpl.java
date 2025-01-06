package com.example.lokakarya.Services.Impl;

import java.util.*;
import java.util.stream.Collectors;

import com.example.lokakarya.Repository.AssessmentSummaryRepo;
import com.example.lokakarya.Services.AssessmentSummaryServ;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.lokakarya.Entity.AttitudeSkill;
import com.example.lokakarya.Entity.EmpAttitudeSkill;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.AttitudeSkillRepo;
import com.example.lokakarya.Repository.EmpAttitudeSkillRepo;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.EmpAttitudeSkillServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAttitudeSkillServImpl.class);

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Autowired
    AssessmentSummaryServ assessmentSummaryServ;



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
        Log.info("Start createEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        Optional<AttitudeSkill> attitudeSkillOpt = attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId());
        Optional<User> userOpt = userRepo.findById(empAttitudeSkillDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (attitudeSkillOpt.isEmpty()) {
            throw new RuntimeException("AttitudeSkill not found with ID: " + empAttitudeSkillDto.getAttitudeSkillId());
        }
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empAttitudeSkillDto.getUserId());
        }

        AssessmentSummaryServ assessmentSummaryServ = new AssessmentSummaryServImpl();
        assessmentSummaryServ.calculateAndSaveScoreForUser(empAttitudeSkillDto.getUserId() , empAttitudeSkillDto.getAssessmentYear());
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillDto.toEntity(
            empAttitudeSkillDto,
            attitudeSkillOpt.get(),
            userOpt.get(),
            null,
            null, 
            currentUser, 
            new java.util.Date() 
        );
        empAttitudeSkill.setUser(userOpt.get());
        empAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);
        Log.info("End createEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill);
    }

    @Override
    public List<EmpAttitudeSkillReqDto> getEmpAttitudeSkillByUserId(UUID userId) {
        Log.info("Start getEmpAttitudeSkillByUserId in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> empAttitudeSkillList = empAttitudeSkillRepo.findByUserId(userId);
        List<EmpAttitudeSkillReqDto> empAttitudeSkillDtos = empAttitudeSkillList.stream()
            .map(EmpAttitudeSkillReqDto::fromEntity)
            .collect(Collectors.toList());
        Log.info("End getEmpAttitudeSkillByUserId in EmpAttitudeSkillServImpl");
        return empAttitudeSkillDtos;
    }

    @Override
    public List<EmpAttitudeSkillReqDto> getEmpAttitudeSkillsByUserIdAndYear(UUID userId, Integer assessmentYear) {
        Log.info("Start getEmpAttitudeSkillByUserIdAndYear in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> empAttitudeSkillList = empAttitudeSkillRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);
        List<EmpAttitudeSkillReqDto> empAttitudeSkillDtos = empAttitudeSkillList.stream()
            .map(EmpAttitudeSkillReqDto::fromEntity)
            .collect(Collectors.toList());
        Log.info("End getEmpAttitudeSkillByUserIdAndYear in EmpAttitudeSkillServImpl");
        return empAttitudeSkillDtos;
    }

    @Override
    public List<EmpAttitudeSkillReqDto> getEmpAttitudeSkillByYear(Integer assessmentYear) {
        Log.info("Start getEmpAttitudeSkillByYear in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> empAttitudeSkillList = empAttitudeSkillRepo.findByAssessmentYear(assessmentYear);
        List<EmpAttitudeSkillReqDto> empAttitudeSkillDtos = empAttitudeSkillList.stream()
            .map(EmpAttitudeSkillReqDto::fromEntity)
            .collect(Collectors.toList());
        Log.info("End getEmpAttitudeSkillByYear in EmpAttitudeSkillServImpl");
        return empAttitudeSkillDtos;
    }


    @Override
    @Transactional
    public List<EmpAttitudeSkillReqDto> createAllEmpAttitudeSkill(List<EmpAttitudeSkillDto> empAttitudeSkillDtos) {
        Log.info("Start createAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        empAttitudeSkillRepo.deleteByUserIdAndAssessmentYear(empAttitudeSkillDtos.get(0).getUserId(), empAttitudeSkillDtos.get(0).getAssessmentYear());
        List<EmpAttitudeSkill> empAttitudeSkills = empAttitudeSkillDtos.stream().map(empAttitudeSkillDto -> {
            Optional<AttitudeSkill> attitudeSkillOpt = attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId());
            Optional<User> userOpt = userRepo.findById(empAttitudeSkillDto.getUserId());
            UUID currentUser = getUserUtil.getCurrentUser().getId();
            if (attitudeSkillOpt.isEmpty()) {
                throw new RuntimeException("Attitude Skill not found with ID: " + empAttitudeSkillDto.getAttitudeSkillId());
            }
            if (userOpt.isEmpty()) {
                    throw new RuntimeException("User not found with ID: " + empAttitudeSkillDto.getUserId());
            }

            return empAttitudeSkillDto.toEntity(
                empAttitudeSkillDto,
                attitudeSkillOpt.get(),
                userOpt.get(),
                null,
                null,
                currentUser,
                new Date()
            );
        }).collect(Collectors.toList());
        List<EmpAttitudeSkill> savedSkills = empAttitudeSkillRepo.saveAll(empAttitudeSkills);
        EmpAttitudeSkillDto firstDto = empAttitudeSkillDtos.get(0);
        UUID userId = firstDto.getUserId();
        int year = firstDto.getAssessmentYear();
        assessmentSummaryServ.calculateAndSaveScoreForUser(userId , year);
        Log.info("End createAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return savedSkills.stream()
                        .map(EmpAttitudeSkillReqDto::fromEntity)
                        .collect(Collectors.toList());
    }
    @Override
    public EmpAttitudeSkillReqDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillDto) {
        Log.info("Start updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Attitude Skill not found"));
        empAttitudeSkill.setUser(userRepo.findById(empAttitudeSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));    
        empAttitudeSkill.setAttitudeSkill(attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId()).orElseThrow(() -> new RuntimeException("Attitude Skill not found")));
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        empAttitudeSkill.setUpdatedBy(currentUser);
        empAttitudeSkill.setUpdatedAt(new java.util.Date());
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
    @Override
    @Transactional
    public List<EmpAttitudeSkillReqDto> createAllEmpAttitudeSkill2(List<EmpAttitudeSkillDto> empAttitudeSkillDtos) {
        Log.info("Start createAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        empAttitudeSkillRepo.deleteByUserIdAndAssessmentYear(empAttitudeSkillDtos.get(0).getUserId(), empAttitudeSkillDtos.get(0).getAssessmentYear());
        List<EmpAttitudeSkill> empAttitudeSkills = empAttitudeSkillDtos.stream().map(empAttitudeSkillDto -> {
            Optional<AttitudeSkill> attitudeSkillOpt = attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId());
            Optional<User> userOpt = userRepo.findById(empAttitudeSkillDto.getUserId());
            UUID currentUser = getUserUtil.getCurrentUser().getId();
            if (attitudeSkillOpt.isEmpty()) {
                throw new RuntimeException("Attitude Skill not found with ID: " + empAttitudeSkillDto.getAttitudeSkillId());
            }
            if (userOpt.isEmpty()) {
                throw new RuntimeException("User not found with ID: " + empAttitudeSkillDto.getUserId());
            }

            return empAttitudeSkillDto.toEntity(
                    empAttitudeSkillDto,
                    attitudeSkillOpt.get(),
                    userOpt.get(),
                    null,
                    null,
                    currentUser,
                    new Date()
            );
        }).collect(Collectors.toList());
        List<EmpAttitudeSkill> savedSkills = empAttitudeSkillRepo.saveAll(empAttitudeSkills);
        EmpAttitudeSkillDto firstDto = empAttitudeSkillDtos.get(0);
        UUID userId = firstDto.getUserId();
        int year = firstDto.getAssessmentYear();
//        assessmentSummaryServ.calculateAndSaveScoreForUser(userId , year);
        Log.info("End createAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return savedSkills.stream()
                .map(EmpAttitudeSkillReqDto::fromEntity)
                .collect(Collectors.toList());
    }




}
