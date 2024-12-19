package com.example.lokakarya.Services.Impl;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanReqDto;
import com.example.lokakarya.Entity.DevPlan;
import com.example.lokakarya.Entity.EmpDevPlan;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.DevPlanRepo;
import com.example.lokakarya.Repository.EmpDevPlanRepo;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.EmpDevPlanServ;
import com.example.lokakarya.util.GetUserUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpDevPlanServImpl implements EmpDevPlanServ {
    private final Logger Log = LoggerFactory.getLogger(EmpDevPlanServImpl.class);

    @Autowired
    private EmpDevPlanRepo empDevPlanRepo;

    @Autowired
    private DevPlanRepo devPlanRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GetUserUtil getUserUtil;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<EmpDevPlanReqDto> getAllEmpDevPlan() {
        Log.info("Start getAllEmpDevPlan in EmpDevPlanServImpl");
        List<EmpDevPlan> response = empDevPlanRepo.findAll();
        List<EmpDevPlanReqDto> empDevPlanReqDto = new ArrayList<>();
        for (EmpDevPlan empDevPlan : response) {
            empDevPlanReqDto.add(EmpDevPlanReqDto.fromEntity(empDevPlan));
        }
        Log.info("End getAllEmpDevPlan in EmpDevPlanServImpl");
        return empDevPlanReqDto;
    }

    @Override
    public EmpDevPlanReqDto getEmpDevPlanById(UUID id) {
        Log.info("Start getEmpDevPlanById in EmpDevPlanServImpl");
        EmpDevPlan empDevPlan = empDevPlanRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpDevPlan not found"));
        Log.info("End getEmpDevPlanById in EmpDevPlanServImpl");
        return EmpDevPlanReqDto.fromEntity(empDevPlan);
    }

    @Transactional
    @Override
    public List<EmpDevPlanReqDto> createEmpDevPlans(List<EmpDevPlanDto> empDevPlanDtos) {
        Log.info("Start createEmpDevPlans in EmpDevPlanServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        empDevPlanRepo.deleteByUserIdAndAssessmentYear(currentUserId, currentYear);
        entityManager.flush();

        List<EmpDevPlan> empDevPlans = new ArrayList<>();
        for (EmpDevPlanDto empDevPlanDto : empDevPlanDtos) {
            empDevPlanDto.setUser(currentUserId);
            System.out.println("ini id user " + empDevPlanDto.getUser());
            System.out.println("ini id devplan " + empDevPlanDto.getDevPlan());

            Optional<DevPlan> devPlan = devPlanRepo.findById(empDevPlanDto.getDevPlan());
            Optional<User> user = userRepo.findById(empDevPlanDto.getUser());

            if (devPlan.isPresent() && user.isPresent()) {
                EmpDevPlan empDevPlan = EmpDevPlanDto.toEntity(empDevPlanDto, currentUserId, new Date(), null, null);
                empDevPlan.setDevPlan(devPlan.get());
                empDevPlan.setUser(user.get());
                empDevPlans.add(empDevPlan);
            } else {
                throw new RuntimeException("User or DevPlan not found for ID: " + empDevPlanDto.getUser() + " or " + empDevPlanDto.getDevPlan());
            }
        }
        empDevPlanRepo.saveAll(empDevPlans); 
        entityManager.flush(); 

        Log.info("End createEmpDevPlans in EmpDevPlanServImpl");
        return empDevPlans.stream()
                .map(EmpDevPlanReqDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmpDevPlanReqDto updateEmpDevPlan(UUID id, EmpDevPlanDto empDevPlanDto) {
        Log.info("Start updateEmpDevPlan in EmpDevPlanServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        EmpDevPlan empDevPlan = empDevPlanRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpDevPlan not found"));
        if (empDevPlanDto != null) {
            Optional<DevPlan> devPlan = devPlanRepo.findById(empDevPlanDto.getDevPlan());
            Optional<User> user = userRepo.findById(empDevPlanDto.getUser());
            if (devPlan.isPresent() && user.isPresent()) {
                empDevPlan.setDevPlan(devPlan.get());
                empDevPlan.setUser(user.get());
                empDevPlan.setUpdatedBy(currentUserId);
                empDevPlan.setUpdatedAt(new Date());
            } else {
                throw new RuntimeException("User or DevPlan not found");
            }
        }
        empDevPlan.setId(id);
        empDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        empDevPlanRepo.save(empDevPlan);

        Log.info("End updateEmpDevPlan in EmpDevPlanServImpl");
        return EmpDevPlanReqDto.fromEntity(empDevPlan);
    }

    @Override
    public Boolean deleteEmpDevPlan(UUID id) {
        Log.info("Start deleteEmpDevPlan in EmpDevPlanServImpl");
        if (!empDevPlanRepo.existsById(id)) throw new RuntimeException("EmpDevPlan not found");
        empDevPlanRepo.deleteById(id);
        Log.info("End deleteEmpDevPlan in EmpDevPlanServImpl");
        return true;
    }

    @Override
    public List<EmpDevPlanReqDto> getByUserIdAndYear(UUID userId, Integer year) {
        Log.info("Start getByUserIdAndYear in EmpDevPlanServImpl");
        List<EmpDevPlan> response = empDevPlanRepo.findEmpDevPlanByUserIdAndAssessmentYear(userId, year);
        List<EmpDevPlanReqDto> empDevPlanReqDto = new ArrayList<>();
        for (EmpDevPlan empDevPlan : response) {
            empDevPlanReqDto.add(EmpDevPlanReqDto.fromEntity(empDevPlan));
        }
        Log.info("End getByUserIdAndYear in EmpDevPlanServImpl");
        return empDevPlanReqDto;
    }
}
