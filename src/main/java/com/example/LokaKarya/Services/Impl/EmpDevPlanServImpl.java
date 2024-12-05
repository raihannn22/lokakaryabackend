package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanReqDto;
import com.example.LokaKarya.Entity.DevPlan;
import com.example.LokaKarya.Entity.EmpDevPlan;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.DevPlanRepo;
import com.example.LokaKarya.Repository.EmpDevPlanRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.EmpDevPlanServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<EmpDevPlanReqDto> getAllEmpDevPlan() {
        List<EmpDevPlan> response = empDevPlanRepo.findAll();
        List<EmpDevPlanReqDto> empDevPlanReqDto = new ArrayList<>();
        for (EmpDevPlan empDevPlan : response) {
            empDevPlanReqDto.add(EmpDevPlanReqDto.fromEntity(empDevPlan));
        }
        return empDevPlanReqDto;
    }

    @Override
    public EmpDevPlanReqDto getEmpDevPlanById(UUID id) {
        EmpDevPlan empDevPlan = empDevPlanRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpDevPlan not found"));
        return EmpDevPlanReqDto.fromEntity(empDevPlan);
    }

    @Override
    public EmpDevPlanReqDto createEmpDevPlan(EmpDevPlanDto empDevPlanDto) {
        Log.info("Start createEmpDevPlan in EmpDevPlanServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        empDevPlanRepo.deleteByUserId(currentUserId);
        empDevPlanDto.setDevPlan(currentUserId);
        EmpDevPlan empDevPlan = EmpDevPlanDto.toEntity(empDevPlanDto, currentUserId, new Date(), null,null);
        if (empDevPlanDto != null) {
            Optional<DevPlan> devPlan = devPlanRepo.findById(empDevPlanDto.getDevPlan());
            Optional<User> user = userRepo.findById(empDevPlanDto.getUser());
            if (devPlan.isPresent() && user.isPresent()) {
                empDevPlan.setDevPlan(devPlan.get());
                empDevPlan.setUser(user.get());
            } else {
                throw new RuntimeException("User or DevPlan not found");
            }
        }
        empDevPlanRepo.save(empDevPlan);
        Log.info("End createEmpDevPlan in EmpDevPlanServImpl");
        return EmpDevPlanReqDto.fromEntity(empDevPlan);
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
        if (!empDevPlanRepo.existsById(id)) throw new RuntimeException("EmpDevPlan not found");
        empDevPlanRepo.deleteById(id);
        return true;
    }

    @Override
    public List<EmpDevPlanReqDto> getByUserIdAndYear(UUID userId, Integer year) {
        List<EmpDevPlan> response = empDevPlanRepo.findEmpDevPlanByUserIdAndAssessmentYear(userId, year);
        List<EmpDevPlanReqDto> empDevPlanReqDto = new ArrayList<>();
        for (EmpDevPlan empDevPlan : response) {
            empDevPlanReqDto.add(EmpDevPlanReqDto.fromEntity(empDevPlan));
        }
        return empDevPlanReqDto;
    }
}
