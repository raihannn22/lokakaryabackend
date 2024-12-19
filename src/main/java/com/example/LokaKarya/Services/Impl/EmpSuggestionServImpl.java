package com.example.lokakarya.Services.Impl;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.lokakarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.lokakarya.Entity.EmpSuggestion;
import com.example.lokakarya.Entity.EmpTechnicalSkill;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.EmpSuggestionRepo;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.EmpSuggestionServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class EmpSuggestionServImpl implements EmpSuggestionServ {

    private final Logger Log = LoggerFactory.getLogger(EmpSuggestionServImpl.class);

    @Autowired
    private EmpSuggestionRepo empSuggestionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<EmpSuggestionReqDto> getAllEmpSuggestion() {
       Log.info("Start getAllEmpSuggestion in EmpSuggestionServImpl");
        List<EmpSuggestion> response = empSuggestionRepo.findAll();
        List<EmpSuggestionReqDto> empSuggestionReqDto = new ArrayList<>();

        for (EmpSuggestion empSuggestion : response) {
            empSuggestionReqDto.add(EmpSuggestionReqDto.fromEntity(empSuggestion));
        }
       Log.info("End getAllEmpSuggestiont in EmpSuggestionServImpl");
        return empSuggestionReqDto;
    }

    @Override
    public EmpSuggestionReqDto getEmpSuggestionById(UUID id) {
        Log.info("Start getEmpSuggestionById in EmpSuggestionServImpl");
        EmpSuggestion empSuggestion = empSuggestionRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill not found"));
        Log.info("End getEmpSuggestionById in EmpSuggestionServImpl");
        return EmpSuggestionReqDto.fromEntity(empSuggestion);
    }

    @Override
    public EmpSuggestionReqDto createEmpSuggestion (EmpSuggestionDto empSuggestionDto) {
        Log.info("Start createEmpSuggestion in EmpSuggestionServImpl");
        Optional<User> userOpt = userRepo.findById(empSuggestionDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empSuggestionDto.getUserId());
        }
        EmpSuggestion empSuggestion = empSuggestionDto.toEntity(
            empSuggestionDto,
            userOpt.get(),
            null,
            null,
            currentUser, 
            new java.util.Date() 
        );
        empSuggestion = empSuggestionRepo.save(empSuggestion);
        Log.info("End createEmpSuggestion in EmpSuggestionServImpl");
        return EmpSuggestionReqDto.fromEntity(empSuggestion);
    }

    @Override
    public List<EmpSuggestionReqDto> getEmpSuggestionByUserId(UUID userId) {
        Log.info("Start getEmpSuggestionByUserId in EmpSuggestionServImpl");
        List<EmpSuggestion> empSuggestionList = empSuggestionRepo.findByUserId(userId);
        List<EmpSuggestionReqDto> empSuggestionDtos = empSuggestionList.stream()
            .map(EmpSuggestionReqDto::fromEntity)
            .collect(Collectors.toList());
        Log.info("End getEmpSuggestionByUserId in EmpSuggestionServImpl");
        return empSuggestionDtos;
    }

    @Override
    public List<EmpSuggestionReqDto> getEmpSuggestionsByUserIdAndYear(UUID userId, Integer assessmentYear) {
        Log.info("Start getEmpSuggestionByUserIdAndYear in EmpSuggestionServImpl");
        List<EmpSuggestion> empSuggestionList = empSuggestionRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);
        List<EmpSuggestionReqDto> empSuggestionDtos = empSuggestionList.stream()
            .map(EmpSuggestionReqDto::fromEntity)
            .collect(Collectors.toList());

        Log.info("End getEmpSuggestionByUserIdAndYear in EmpSuggestionServImpl");
        return empSuggestionDtos;
    }

        @Transactional
        @Override
        public List<EmpSuggestionReqDto> createAllEmpSuggestion(List<EmpSuggestionDto> empSuggestionDtos) {
            Log.info("Start createAllEmpSuggestion in EmpSuggestionServImpl");
            List<EmpSuggestion> empSuggestions = empSuggestionDtos.stream().map(empSuggestionDto -> {

                Optional<User> userOpt = userRepo.findById(empSuggestionDto.getUserId());
                UUID currentUser = getUserUtil.getCurrentUser().getId();
                Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

                if (userOpt.isEmpty()) {
                        throw new RuntimeException("Suggestion not found with ID: " + empSuggestionDto.getUserId());
                }

                empSuggestionRepo.deleteByAssessmentYearAndUserId( currentYear, currentUser);

                entityManager.flush();
                return empSuggestionDto.toEntity(
                    empSuggestionDto,
                    userOpt.get(), 
                    null,
                    null,
                    currentUser,
                    new java.util.Date() 
                );
            }).collect(Collectors.toList());
            List<EmpSuggestion> savedSkills = empSuggestionRepo.saveAll(empSuggestions);
            Log.info("End createAllEmpSuggestion in EmpSuggestionServImpl");
            return savedSkills.stream()
                            .map(EmpSuggestionReqDto::fromEntity)
                            .collect(Collectors.toList());
        
        }
    @Override
    public EmpSuggestionReqDto updateEmpSuggestion(UUID id, EmpSuggestionDto empSuggestionDto) {
        Log.info("Start updateEmpSuggestion in EmpSuggestionServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        EmpSuggestion empSuggestion = empSuggestionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Achievement Skill not found"));
        empSuggestion.setUser(userRepo.findById(empSuggestionDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));   
        empSuggestion.setSuggestion(empSuggestionDto.getSuggestion());     
        empSuggestion.setAssessmentYear(empSuggestionDto.getAssessmentYear());
        empSuggestion.setUpdatedBy(currentUser);
        empSuggestion.setUpdatedAt(new java.util.Date());
        empSuggestionRepo.save(empSuggestion);
        Log.info("End updateEmpSuggestion in EmpSuggestionServImpl");
        return EmpSuggestionReqDto.fromEntity(empSuggestionRepo.save(empSuggestion));
    }

    @Override
        public Boolean deleteEmpSuggestion(UUID id) {
            Log.info("Start deleteEmpSuggestion in EmpSuggestionServImpl");

            if (empSuggestionRepo.existsById(id)) {
                empSuggestionRepo.deleteById(id);
                Log.info("End deleteEmpSuggestion in EmpSuggestionServImpl");
                return true;
            }
            throw new RuntimeException("EmpSuggestion not found");
        }
    }

