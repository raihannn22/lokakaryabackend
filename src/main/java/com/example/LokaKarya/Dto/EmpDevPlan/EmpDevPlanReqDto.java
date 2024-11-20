package com.example.LokaKarya.Dto.EmpDevPlan;

import com.example.LokaKarya.Entity.DevPlan;
import com.example.LokaKarya.Entity.EmpDevPlan;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpDevPlanReqDto {

    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("USER_ID")
    private User user;

    @JsonProperty("DEV_PLAN_ID")
    private DevPlan devPlan;

    @JsonProperty("ASSESSMENT_YEAR")
    private Integer assessmentYear;

    @JsonProperty("CREATED_BY")
    private UUID createdBy;

    @JsonProperty("CREATED_AT")
    private Date createdAt;

    @JsonProperty("UPDATED_BY")
    private UUID updatedBy;

    @JsonProperty("UPDATED_AT")
    private Date updatedAt;

    public static EmpDevPlanReqDto fromEntity(EmpDevPlan empDevPlan) {
        EmpDevPlanReqDto empDevPlanReqDto = new EmpDevPlanReqDto();
        empDevPlanReqDto.setId(empDevPlan.getId());
        empDevPlanReqDto.setUser(empDevPlan.getUser());
        empDevPlanReqDto.setDevPlan(empDevPlan.getDevPlan());
        empDevPlanReqDto.setAssessmentYear(empDevPlan.getAssessmentYear());
        empDevPlanReqDto.setCreatedBy(empDevPlan.getCreatedBy());
        empDevPlanReqDto.setCreatedAt(empDevPlan.getCreatedAt());
        empDevPlanReqDto.setUpdatedBy(empDevPlan.getUpdatedBy());
        empDevPlanReqDto.setUpdatedAt(empDevPlan.getUpdatedAt());
        return empDevPlanReqDto;
    }
}
