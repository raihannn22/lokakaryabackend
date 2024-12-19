package com.example.lokakarya.Dto.EmpDevPlan;

import com.example.lokakarya.Entity.EmpDevPlan;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private UUID user;

    @JsonProperty("DEV_PLAN_ID")
    private UUID devPlan;

    @JsonProperty("DEV_PLAN_NAME")
    private String devPlanName;

    @JsonProperty("ASSESSMENT_YEAR")
    private Integer assessmentYear;

    @JsonProperty("DETAIL")
    private String detail;

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
        empDevPlanReqDto.setUser(empDevPlan.getUser().getId());
        empDevPlanReqDto.setDevPlan(empDevPlan.getDevPlan().getId());
        empDevPlanReqDto.setDevPlanName(empDevPlan.getDevPlan().getPlan());
        empDevPlanReqDto.setAssessmentYear(empDevPlan.getAssessmentYear());
        empDevPlanReqDto.setDetail(empDevPlan.getDetail());
        empDevPlanReqDto.setCreatedBy(empDevPlan.getCreatedBy());
        empDevPlanReqDto.setCreatedAt(empDevPlan.getCreatedAt());
        empDevPlanReqDto.setUpdatedBy(empDevPlan.getUpdatedBy());
        empDevPlanReqDto.setUpdatedAt(empDevPlan.getUpdatedAt());
        return empDevPlanReqDto;
    }
}
