package com.example.bestpracticespringapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantTest {

    @Test
    public void testGettersAndSetters(){
        Participant participant = new Participant();

        //Set
        participant.setPartId(1L);
        participant.setName("xyz");
        participant.setAge(10);

        //Get
        assertThat(participant.getPartId()).isEqualTo(1L);
        assertThat(participant.getName()).isEqualTo("xyz");
        assertThat(participant.getAge()).isEqualTo(10);
    }

    @Test
    public void testEquality(){
        Participant participant1 = new Participant(1L, "xyz", 10);
        Participant participant2 = new Participant(1L, "xyz", 10);

        //Assert
        assertThat(participant1).isEqualTo(participant2);
    }

    @Test
    public void testHasCode(){
        Participant participant1 = new Participant(1L, "xyz", 10);
        Participant participant2 = new Participant(1L, "xyz", 10);

//        Assert
        assertThat(participant1.hashCode()).isEqualTo(participant2.hashCode());
    }

    @Test
    public void testToString(){
        Participant participant = new Participant(1L, "xyz", 10);

//        Assert
        assertThat(participant.toString()).contains("name=xyz","age=10");
    }
}