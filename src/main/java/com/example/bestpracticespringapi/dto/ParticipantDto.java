package com.example.bestpracticespringapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {
    Long id; // can be removed from dto if not required
    @NotEmpty(message = "Name is mandatory")
    String name;
    @Min(value = 0, message = "Age should be greater than zero")
    Integer age;
}
