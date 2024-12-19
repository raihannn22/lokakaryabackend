package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpDevPlanServ {
    List<EmpDevPlanReqDto> getAllEmpDevPlan();
    EmpDevPlanReqDto getEmpDevPlanById(UUID id);
    List<EmpDevPlanReqDto> createEmpDevPlans(List<EmpDevPlanDto> empDevPlanDtos);
    EmpDevPlanReqDto updateEmpDevPlan(UUID id, EmpDevPlanDto empDevPlanDto);
    Boolean deleteEmpDevPlan(UUID id);
    List<EmpDevPlanReqDto> getByUserIdAndYear(UUID userId, Integer year);
}