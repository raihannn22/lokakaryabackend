package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AppRole.AppRoleDto;
import com.example.lokakarya.Dto.AppRole.AppRoleReqDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;

public interface AppRoleServ {
    List<AppRoleReqDto> getAllAppRole();
    AppRoleReqDto getAppRoleById(UUID id);
    AppRoleReqDto createAppRole(AppRoleDto appRoleDto);
    AppRoleReqDto updateAppRole(UUID id, AppRoleDto appRoleDto);
    Boolean deleteAppRole(UUID id);
}
