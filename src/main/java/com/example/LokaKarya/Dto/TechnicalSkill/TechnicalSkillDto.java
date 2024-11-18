package com.example.LokaKarya.Dto.TechnicalSkill;

import com.example.LokaKarya.Entity.TechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechnicalSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("technical_skill")
    private String technicalSkill;
    @JsonProperty("enabled")
    private Integer enabled;

    public static TechnicalSkillDto fromEntity(TechnicalSkill technicalSkill) {
        TechnicalSkillDto technicalSkillDto = new TechnicalSkillDto();
        technicalSkillDto.setId(technicalSkill.getId());
        technicalSkillDto.setTechnicalSkill(technicalSkill.getTechnicalSkill());
        technicalSkillDto.setEnabled(technicalSkill.getEnabled());
        return technicalSkillDto;
    }

    public static TechnicalSkill toEntity(TechnicalSkillDto technicalSkillDto) {
        TechnicalSkill technicalSkill = new TechnicalSkill();
        technicalSkill.setId(technicalSkillDto.getId());
        technicalSkill.setTechnicalSkill(technicalSkillDto.getTechnicalSkill());
        technicalSkill.setEnabled(technicalSkillDto.getEnabled());
        return technicalSkill;
    }
}

