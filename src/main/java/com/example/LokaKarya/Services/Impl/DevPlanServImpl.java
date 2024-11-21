package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Config.GetUserUtil;
import com.example.LokaKarya.Dto.DevPlan.DevPlanDto;
import com.example.LokaKarya.Dto.DevPlan.DevPlanReqDto;
import com.example.LokaKarya.Entity.DevPlan;
import com.example.LokaKarya.Repository.DevPlanRepo;
import com.example.LokaKarya.Services.DevPlanServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<DevPlan> response = devPlanRepo.findAll();
        List<DevPlanReqDto> devPlanReqDto = new ArrayList<>();
        for (DevPlan devPlan : response) {
            devPlanReqDto.add(DevPlanReqDto.fromEntity(devPlan));
        }
        return devPlanReqDto;
    }

    @Override
    public DevPlanReqDto getDevPlanById(UUID id) {
        DevPlan devPlan = devPlanRepo.findById(id).orElseThrow(()-> new RuntimeException("DevPlan not found"));
        return DevPlanReqDto.fromEntity(devPlan);
    }

    @Override
    public DevPlanReqDto createDevPlan(DevPlanDto devPlanDto) {
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        DevPlan devPlan = devPlanDto.toEntity(devPlanDto,  currentUserId, new java.util.Date() , null, null);
        devPlanRepo.save(devPlan);
        return DevPlanReqDto.fromEntity(devPlan);
    }

    @Override
    public DevPlanReqDto updateDevPlan(UUID id, DevPlanDto devPlanDto) {
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        DevPlan devPlan = devPlanRepo.findById(id).orElseThrow(()-> new RuntimeException("DevPlan not found"));
        DevPlan devPlan1 = devPlanDto.toEntity(devPlanDto, devPlan.getCreatedBy(), devPlan.getCreatedAt() , currentUserId, new Date());
        devPlan1.setId(id);
        return DevPlanReqDto.fromEntity(devPlanRepo.save(devPlan1));
    }

    @Override
    public Boolean deleteDevPlan(UUID id) {
        if(!devPlanRepo.existsById(id)) throw new RuntimeException("DevPlan not found");
        devPlanRepo.deleteById(id);
        return true;
    }
}
