package com.example.exam_postgre.dto;

import com.example.exam_postgre.model.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class GiftRequest {

    @NotBlank(message = "Gift name can't be empty")
    private String name;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age can't be negative")
    @Max(value = 100, message = "Age must be under 100")
    private Integer targetAge;
}