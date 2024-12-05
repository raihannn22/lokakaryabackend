package com.example.LokaKarya.Dto.EmpDevPlan;

import com.example.LokaKarya.Entity.DevPlan;
import com.example.LokaKarya.Entity.EmpDevPlan;
import com.example.LokaKarya.Entity.User;
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

    @JsonProperty("DEV_PLAN_ID")
    private UUID devPlan;

    @JsonProperty("ASSESSMENT_YEAR")
    private Integer assessmentYear;

    public static EmpDevPlan toEntity(EmpDevPlanDto empDevPlanDto, UUID CreatedBy, Date CreatedAt, UUID UpdatedBy, Date UpdatedAt) {
        EmpDevPlan empDevPlan = new EmpDevPlan();
        empDevPlan.setId(empDevPlanDto.getId());
//        empDevPlan.setUser(empDevPlanDto.getUser().getId());
//        empDevPlan.setDevPlan(empDevPlanDto.getDevPlan().getId());
        empDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        empDevPlan.setCreatedBy(CreatedBy);
        empDevPlan.setCreatedAt(CreatedAt);
        empDevPlan.setUpdatedBy(UpdatedBy);
        empDevPlan.setUpdatedAt(UpdatedAt);
        return empDevPlan;
    }
}
