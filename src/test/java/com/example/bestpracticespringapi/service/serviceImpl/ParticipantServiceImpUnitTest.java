package com.example.bestpracticespringapi.service.serviceImpl;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.model.Participant;
import com.example.bestpracticespringapi.repository.ParticipantRepository;
import com.example.bestpracticespringapi.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class ParticipantServiceImpUnitTest {

    @Mock
    ModelMapper modelMapper;

    @Mock
    ParticipantRepository participantRepository;

    @InjectMocks
    // Adding manually via setup as model mapper was not getting injected
    // Later started working when added ModelMapper in constructor of service
    private ParticipantServiceImpl participantService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        participantService = new ParticipantServiceImpl(modelMapper, participantRepository);
//    }

    @Test
    void getAllParticipants() {
        //Mock repo response data
        List<Participant> participants = new ArrayList<>();
        Participant participant1 = new Participant(1L, "xyz", 15);
        Participant participant2 = new Participant(2L, "abc", 25);
        participants.add(participant1);
        participants.add(participant2);

        //Mock Response
        List<ParticipantDto> participantDtos = new ArrayList<>();
        ParticipantDto participantDto1 = new ParticipantDto(1L, "xyz", 15);
        ParticipantDto participantDto2 = new ParticipantDto(2L, "abc", 25);
        participantDtos.add(participantDto1);
        participantDtos.add(participantDto2);

        //Mock repo
        when(modelMapper.map(participant1, ParticipantDto.class)).thenReturn(participantDto1);
        when(modelMapper.map(participant2, ParticipantDto.class)).thenReturn(participantDto2);
        when(participantRepository.findAll()).thenReturn(participants);

        //Call
        List<ParticipantDto> response = participantService.getAllParticipants();

        //Assert
        assertIterableEquals(participantDtos, response);
    }
}
