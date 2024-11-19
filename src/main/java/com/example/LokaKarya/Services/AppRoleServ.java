package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AppRole.AppRoleDto;
import com.example.LokaKarya.Dto.AppRole.AppRoleReqDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;

import java.util.List;
import java.util.UUID;

public interface AppRoleServ {
    List<AppRoleReqDto> getAllAppRole();
    AppRoleReqDto getAppRoleById(UUID id);
    AppRoleReqDto createAppRole(AppRoleDto appRoleDto);
    AppRoleReqDto updateAppRole(UUID id, AppRoleDto appRoleDto);
    Boolean deleteAppRole(UUID id);
}
