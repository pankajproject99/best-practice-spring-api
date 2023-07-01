package com.example.bestpracticespringapi.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantDtoTest {

    @Test
    void testEquals() {
        ParticipantDto participantDto1 = new ParticipantDto(1L, "xyz", 10);
        ParticipantDto participantDto2 = new ParticipantDto(1L, "xyz", 10);

        //Assert
        assertThat(participantDto1).isEqualTo(participantDto2);
    }

    @Test
    void testHashCode() {
        ParticipantDto participantDto1 = new ParticipantDto(1L, "xyz", 10);
        ParticipantDto participantDto2 = new ParticipantDto(1L, "xyz", 10);

        //Assert
        assertThat(participantDto1.hashCode()).isEqualTo(participantDto2.hashCode());
    }

    @Test
    void testToString() {
        ParticipantDto participantDto1 = new ParticipantDto(1L, "xyz", 10);

        //Assert
        assertThat(participantDto1.toString()).contains("name=xyz","age=10");
    }
}