package com.example.bestpracticespringapi.dto;

import lombok.Data;

@Data
public class ParticipantDto {
    Long id; // can be removed from dto if not required
    String name;
    Integer age;
}
