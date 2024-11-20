package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.DevPlan.DevPlanDto;
import com.example.LokaKarya.Dto.DevPlan.DevPlanReqDto;
import com.example.LokaKarya.Dto.Division.DivisionDto;
import com.example.LokaKarya.Dto.Division.DivisionReqDto;

import java.util.List;
import java.util.UUID;

public interface DevPlanServ {
    List<DevPlanReqDto> getAllDevPlan();
    DevPlanReqDto getDevPlanById(UUID id);
    DevPlanReqDto createDevPlan(DevPlanDto devPlanDto);
    DevPlanReqDto updateDevPlan(UUID id, DevPlanDto devPlanDto);
    Boolean deleteDevPlan(UUID id);
}
