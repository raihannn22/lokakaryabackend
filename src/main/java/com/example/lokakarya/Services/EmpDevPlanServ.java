package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.lokakarya.Dto.EmpDevPlan.EmpDevPlanReqDto;

public interface EmpDevPlanServ {
    List<EmpDevPlanReqDto> getAllEmpDevPlan();
    EmpDevPlanReqDto getEmpDevPlanById(UUID id);
    List<EmpDevPlanReqDto> createEmpDevPlans(List<EmpDevPlanDto> empDevPlanDtos);
    EmpDevPlanReqDto updateEmpDevPlan(UUID id, EmpDevPlanDto empDevPlanDto);
    Boolean deleteEmpDevPlan(UUID id);
    List<EmpDevPlanReqDto> getByUserIdAndYear(UUID userId, Integer year);
}