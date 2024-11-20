package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanDto;
import com.example.LokaKarya.Dto.EmpDevPlan.EmpDevPlanReqDto;

import java.util.List;
import java.util.UUID;

public interface EmpDevPlanServ {
    List<EmpDevPlanReqDto> getAllEmpDevPlan();
    EmpDevPlanReqDto getEmpDevPlanById(UUID id);
    EmpDevPlanReqDto createEmpDevPlan(EmpDevPlanDto empDevPlanDto);
    EmpDevPlanReqDto updateEmpDevPlan(UUID id, EmpDevPlanDto empDevPlanDto);
    Boolean deleteEmpDevPlan(UUID id);
}
