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
public class DevPlanReqDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("PLAN")
    private String plan;

    @JsonProperty("ENABLED")
    private Integer enabled;

    @JsonProperty("CREATED_BY")
    private UUID createdBy;

    @JsonProperty("CREATED_AT")
    private Date createdAt = new Date(System.currentTimeMillis());

    @JsonProperty("UPDATED_BY")
    private UUID updatedBy;

    @JsonProperty("UPDATED_AT")
    private Date updatedAt;

    public static DevPlanReqDto fromEntity(DevPlan devPlan) {
        DevPlanReqDto devPlanReqDto = new DevPlanReqDto();
        devPlanReqDto.setId(devPlan.getId());
        devPlanReqDto.setPlan(devPlan.getPlan());
        devPlanReqDto.setEnabled(devPlan.getEnabled());
        devPlanReqDto.setCreatedBy(devPlan.getCreatedBy());
        devPlanReqDto.setCreatedAt(devPlan.getCreatedAt());
        devPlanReqDto.setUpdatedBy(devPlan.getUpdatedBy());
        devPlanReqDto.setUpdatedAt(devPlan.getUpdatedAt());
        return devPlanReqDto;
    }
}
