package com.example.bestpracticespringapi.service.serviceImpl;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.model.Participant;
import com.example.bestpracticespringapi.repository.ParticipantRepository;
import com.example.bestpracticespringapi.service.ParticipantService;
import jakarta.servlet.http.Part;
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
import static org.mockito.Mockito.when;

@SpringBootTest // so that we have sprint context, this becomes more or integration test
    // But this simplfies as from spring context you can call any bean.
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
        // Asset all objects
        for( int i = 0; i < participantsMock.size(); i++){
            assertEquals(modelMapper.map(participantsMock.get(i), ParticipantDto.class), participantDtosResponse.get(i));
        }

    }

    @Test
    void createParticipant() {
        // Don't have to mock ModelMapper behaviour, this is simple
        //Mock Data
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setName("xyz");
        participantDto.setAge(10);

        //Mock repo
        Participant participant = new Participant();
        participant.setPartId(1L);
        participant.setName("xyz");
        participant.setAge(10);

        Participant participantInput = modelMapper.map(participantDto, Participant.class);
        when(participantRepository.save(participantInput)).thenReturn(participant);
        ParticipantDto participantDtoOutput = modelMapper.map(participant, ParticipantDto.class);

        //call
        ParticipantDto participantResponse = participantService.createParticipant(participantDto);


        //Assert
        assertEquals(participantDtoOutput, participantResponse);

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