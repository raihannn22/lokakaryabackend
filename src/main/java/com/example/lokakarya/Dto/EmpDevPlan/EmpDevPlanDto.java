package com.example.lokakarya.Dto.EmpDevPlan;

import com.example.lokakarya.Entity.EmpDevPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpDevPlanDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonIgnoreProperties
    @JsonIgnore
    private UUID user;

    @JsonProperty("DETAIL")
    private String detail;

    @JsonProperty("DEV_PLAN_ID")
    private UUID devPlan;

    @JsonProperty("ASSESSMENT_YEAR")
    private Integer assessmentYear;

    public static EmpDevPlan toEntity(EmpDevPlanDto empDevPlanDto, UUID CreatedBy, Date CreatedAt, UUID UpdatedBy, Date UpdatedAt) {
        EmpDevPlan empDevPlan = new EmpDevPlan();
        empDevPlan.setId(empDevPlanDto.getId());
        empDevPlan.setDetail(empDevPlanDto.getDetail());
        empDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        empDevPlan.setCreatedBy(CreatedBy);
        empDevPlan.setCreatedAt(CreatedAt);
        empDevPlan.setUpdatedBy(UpdatedBy);
        empDevPlan.setUpdatedAt(UpdatedAt);
        return empDevPlan;
    }
}
