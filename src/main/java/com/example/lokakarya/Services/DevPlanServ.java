package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.DevPlan.DevPlanDto;
import com.example.lokakarya.Dto.DevPlan.DevPlanReqDto;

public interface DevPlanServ {
    List<DevPlanReqDto> getAllDevPlan();
    DevPlanReqDto getDevPlanById(UUID id);
    DevPlanReqDto createDevPlan(DevPlanDto devPlanDto);
    DevPlanReqDto updateDevPlan(UUID id, DevPlanDto devPlanDto);
    Boolean deleteDevPlan(UUID id);
    List<DevPlanReqDto> getPaginatedDevPlan(int page, int size, String sort, String direction, String searchKeyword); // Hapus percentage
    long count();
    long countBySearchKeyword(String searchKeyword);
}