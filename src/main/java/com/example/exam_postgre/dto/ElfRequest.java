package com.example.exam_postgre.dto;

import com.example.exam_postgre.model.SkillLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElfRequest {

    @NotBlank(message = "Elf name is required")
    private String name;

    @NotNull(message = "Skill level is required")
    private SkillLevel skillLevel;
}