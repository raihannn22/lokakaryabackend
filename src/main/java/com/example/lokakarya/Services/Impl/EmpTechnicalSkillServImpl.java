package com.example.lokakarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.lokakarya.Entity.EmpAttitudeSkill;
import com.example.lokakarya.Entity.EmpTechnicalSkill;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.EmpTechnicalSkillRepo;
import com.example.lokakarya.Repository.TechnicalSkillRepo;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.EmpTechnicalSkillServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillServImpl.class);

    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

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
        Log.info("Start createEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        Optional<TechnicalSkill> technicalSkillOpt = technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId());
        Optional<User> userOpt = userRepo.findById(empTechnicalSkillDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (technicalSkillOpt.isEmpty()) {
            throw new RuntimeException("Technical Skill not found with ID: " + empTechnicalSkillDto.getTechnicalSkillId());
        }
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empTechnicalSkillDto.getUserId());
        }
        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillDto.toEntity(
            empTechnicalSkillDto,
            technicalSkillOpt.get(),
            userOpt.get(),
            null, 
            null,
            currentUser, 
            new java.util.Date() 
        );
        EmpTechnicalSkill savedEntity = empTechnicalSkillRepo.save(empTechnicalSkill);
        Log.info("End createEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillReqDto.fromEntity(savedEntity);
    }

    @Override
    public List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillByUserId(UUID userId) {
        Log.info("Start getEmpTechnicalSkillByUserId in EmpTechnicalSkillServImpl");
        List<EmpTechnicalSkill> empTechnicalSkillList = empTechnicalSkillRepo.findByUserId(userId);
        List<EmpTechnicalSkillReqDto> empTechnicalSkillDtos = empTechnicalSkillList.stream()
            .map(EmpTechnicalSkillReqDto::fromEntity)
            .collect(Collectors.toList());

        Log.info("End getEmpTechnicalSkillByUserId in EmpTechnicalSkillServImpl");
        return empTechnicalSkillDtos;
    }

        @Override
        public List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillsByUserIdAndYear(UUID userId, Integer assessmentYear) {
            Log.info("Start getEmpTechnicalSkillByUserIdAndYear in EmpTechnicalSkillServImpl");
            List<EmpTechnicalSkill> empTechnicalSkillList = empTechnicalSkillRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);
            List<EmpTechnicalSkillReqDto> empTechnicalSkillDtos = empTechnicalSkillList.stream()
                .map(EmpTechnicalSkillReqDto::fromEntity)
                .collect(Collectors.toList());

            Log.info("End getEmpTechnicalSkillByUserIdAndYear in EmpTechnicalSkillServImpl");
            return empTechnicalSkillDtos;
        }
        @Override
        public List<EmpTechnicalSkillReqDto> createAllEmpTechnicalSkill(List<EmpTechnicalSkillDto> empTechnicalSkillDtos) {
            Log.info("Start createAllEmpTechnicalSkill in EmpTechnicalSkillServImpl");
            List<EmpTechnicalSkill> empTechnicalSkills = empTechnicalSkillDtos.stream().map(empTechnicalSkillDto -> {
                Optional<TechnicalSkill> technicalSkillOpt = technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId());
                Optional<User> userOpt = userRepo.findById(empTechnicalSkillDto.getUserId());
                UUID currentUser = getUserUtil.getCurrentUser().getId();
                if (technicalSkillOpt.isEmpty()) {
                    throw new RuntimeException("Technical Skill not found with ID: " + empTechnicalSkillDto.getTechnicalSkillId());
                }
                if (userOpt.isEmpty()) {
                        throw new RuntimeException("User not found with ID: " + empTechnicalSkillDto.getUserId());
                }
                return empTechnicalSkillDto.toEntity(
                    empTechnicalSkillDto,
                    technicalSkillOpt.get(),
                    userOpt.get(), 
                    null,
                    null,
                    currentUser,
                    new java.util.Date() 
                );
            }).collect(Collectors.toList());
            List<EmpTechnicalSkill> savedSkills = empTechnicalSkillRepo.saveAll(empTechnicalSkills);
            Log.info("End createAllEmpTechnicalSkill in EmpTechnicalSkillServImpl");
            return savedSkills.stream()
                            .map(EmpTechnicalSkillReqDto::fromEntity)
                            .collect(Collectors.toList());
        
        }
        @Override
        public EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto) {
            Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
            UUID currentUser = getUserUtil.getCurrentUser().getId();
            EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
            empTechnicalSkill.setUser(userRepo.findById(empTechnicalSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));    
            empTechnicalSkill.setTechnicalSkill(technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId()).orElseThrow(() -> new RuntimeException("Technical Skill not found")));
            empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
            empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
            empTechnicalSkill.setUpdatedBy(currentUser);
            empTechnicalSkill.setUpdatedAt(new java.util.Date());
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

    }

