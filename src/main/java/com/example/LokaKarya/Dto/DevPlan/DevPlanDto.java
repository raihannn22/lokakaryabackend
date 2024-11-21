package com.example.LokaKarya.Dto.DevPlan;

import com.example.LokaKarya.Entity.DevPlan;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DevPlanDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("PLAN")
    private String plan;

    @JsonProperty("ENABLED")
    private Integer enabled;

    public static DevPlan toEntity(DevPlanDto devPlanDto, UUID CreatedBy,Date CreatedAt ,UUID updatedBy
            , Date updatedAt) {
        DevPlan devPlan = new DevPlan();
        devPlan.setId(devPlanDto.getId());
        devPlan.setPlan(devPlanDto.getPlan());
        devPlan.setEnabled(devPlanDto.getEnabled());
        devPlan.setCreatedBy(CreatedBy);
        devPlan.setCreatedAt(CreatedAt);
        devPlan.setUpdatedBy(updatedBy);
        devPlan.setUpdatedAt(updatedAt);
        return devPlan;
    }
}
