package com.example.bestpracticespringapi.service.serviceImpl;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.model.Participant;
import com.example.bestpracticespringapi.repository.ParticipantRepository;
import com.example.bestpracticespringapi.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // so that we have sprint context
class ParticipantServiceImplTest {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ParticipantService participantService;

    @MockBean
    ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllParticipants() {
        // Mocking data
        List<Participant> participantsMock = new ArrayList<>();
        Participant participant1 = new Participant(1L, "xyz", 10);
        Participant participant2 = new Participant(2L, "abc", 20);
        participantsMock.add(participant1);
        participantsMock.add(participant2);

        //Mocking repository
        Mockito.when(participantRepository.findAll()).thenReturn(participantsMock);

        //Call
        List<ParticipantDto> participantDtosResponse = participantService.getAllParticipants();

        //Assert
        assertEquals(participantsMock.size(), participantDtosResponse.size());
        assertEquals(modelMapper.map(participantsMock.get(1), ParticipantDto.class), participantDtosResponse.get(1));

    }

    @Test
    void createParticipant() {
    }

    @Test
    void updateParticipant() {
    }

    @Test
    void getParticipantById() {
    }

    @Test
    void deleteParticipantById() {
    }
}