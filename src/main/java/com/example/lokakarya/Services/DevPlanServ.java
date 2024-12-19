package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.DevPlan.DevPlanDto;
import com.example.lokakarya.Dto.DevPlan.DevPlanReqDto;

import java.util.List;
import java.util.UUID;

public interface DevPlanServ {
    List<DevPlanReqDto> getAllDevPlan();
    DevPlanReqDto getDevPlanById(UUID id);
    DevPlanReqDto createDevPlan(DevPlanDto devPlanDto);
    DevPlanReqDto updateDevPlan(UUID id, DevPlanDto devPlanDto);
    Boolean deleteDevPlan(UUID id);
}
