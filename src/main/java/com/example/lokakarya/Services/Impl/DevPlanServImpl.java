package com.example.lokakarya.Services.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.DevPlan.DevPlanDto;
import com.example.lokakarya.Dto.DevPlan.DevPlanReqDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.lokakarya.Entity.DevPlan;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.example.lokakarya.Repository.DevPlanRepo;
import com.example.lokakarya.Services.DevPlanServ;
import com.example.lokakarya.util.GetUserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DevPlanServImpl implements DevPlanServ {
    private static Logger Log = LoggerFactory.getLogger(DevPlanServImpl.class);

    @Autowired
    private DevPlanRepo devPlanRepo;

    @Autowired
    private GetUserUtil getUserUtil;

    @Override
    public List<DevPlanReqDto> getAllDevPlan() {
        Log.info("Start getAllDevPlan in DevPlanServImpl");
        List<DevPlan> response = devPlanRepo.findAll();
        List<DevPlanReqDto> devPlanReqDto = new ArrayList<>();
        for (DevPlan devPlan : response) {
            devPlanReqDto.add(DevPlanReqDto.fromEntity(devPlan));
        }
        Log.info("End getAllDevPlan in DevPlanServImpl");
        return devPlanReqDto;
    }

    @Override
    public List<DevPlanReqDto> getPaginatedDevPlan(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedDevPlan in DevPlanServImpl");
        
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<DevPlan> devPlanPage;

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            devPlanPage = devPlanRepo.findByPlanContainingIgnoreCase(searchKeyword, pageable);
        } else {
            devPlanPage = devPlanRepo.findAll(pageable);
        }

        List<DevPlanReqDto> devPlanReqDto = new ArrayList<>();
        for (DevPlan devPlan : devPlanPage.getContent()) {
            devPlanReqDto.add(DevPlanReqDto.fromEntity(devPlan));
        }
        
        Log.info("End getPaginatedDevPlan in DevPlanServImpl");
        return devPlanReqDto;
    }

    @Override
    public DevPlanReqDto getDevPlanById(UUID id) {
        Log.info("Start getDevPlanById in DevPlanServImpl");
        DevPlan devPlan = devPlanRepo.findById(id).orElseThrow(()-> new RuntimeException("DevPlan not found"));
        Log.info("End getDevPlanById in DevPlanServImpl");
        return DevPlanReqDto.fromEntity(devPlan);
    }

    @Override
    public DevPlanReqDto createDevPlan(DevPlanDto devPlanDto) {
        Log.info("Start createDevPlan in DevPlanServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        DevPlan devPlan = devPlanDto.toEntity(devPlanDto,  currentUserId, new java.util.Date() , null, null);
        devPlanRepo.save(devPlan);
        Log.info("End createDevPlan in DevPlanServImpl");
        return DevPlanReqDto.fromEntity(devPlan);
    }

    @Override
    public DevPlanReqDto updateDevPlan(UUID id, DevPlanDto devPlanDto) {
        Log.info("Start updateDevPlan in DevPlanServImpl");
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        DevPlan devPlan = devPlanRepo.findById(id).orElseThrow(()-> new RuntimeException("DevPlan not found"));
        DevPlan devPlan1 = devPlanDto.toEntity(devPlanDto, devPlan.getCreatedBy(), devPlan.getCreatedAt() , currentUserId, new Date());
        devPlan1.setId(id);
        Log.info("End updateDevPlan in DevPlanServImpl");
        return DevPlanReqDto.fromEntity(devPlanRepo.save(devPlan1));
    }

    @Override
    public Boolean deleteDevPlan(UUID id) {
        Log.info("Start deleteDevPlan in DevPlanServImpl");
        if(!devPlanRepo.existsById(id)) throw new RuntimeException("DevPlan not found");
        devPlanRepo.deleteById(id);
        Log.info("End deleteDevPlan in DevPlanServImpl");
        return true;
    }

    @Override
    public long count() {
        return devPlanRepo.count(); 
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return devPlanRepo.countByPlanContainingIgnoreCase(searchKeyword);
        }
        return devPlanRepo.count(); 
    }
}
