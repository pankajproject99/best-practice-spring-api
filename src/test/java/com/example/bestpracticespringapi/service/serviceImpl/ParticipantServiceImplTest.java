package com.example.bestpracticespringapi.service.serviceImpl;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.example.bestpracticespringapi.model.Participant;
import com.example.bestpracticespringapi.repository.ParticipantRepository;
import com.example.bestpracticespringapi.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
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
        for (int i = 0; i < participantsMock.size(); i++) {
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
    void updateParticipant() throws ResourceNotFoundException {
        //Mock Data
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setName("xyz");
        participantDto.setAge(10);

        //Mock Repo
        Participant participantOutput = new Participant();
        participantOutput.setPartId(1L);
        participantOutput.setName("abc");
        participantOutput.setAge(20);

        when(participantRepository.findById(1L)).thenReturn(java.util.Optional.of(participantOutput));

        Participant participantInput = new Participant();
        participantInput.setPartId(1L);
        participantInput.setName("xyz");
        participantInput.setAge(10);

        when(participantRepository.save(participantInput)).thenReturn(participantInput);

        //call
        ParticipantDto participantDtoResponse = participantService.updateParticipant(1L, participantDto);

        //Assert
        assertEquals(modelMapper.map(participantInput, ParticipantDto.class), participantDtoResponse);
    }

    @Test
    void updateParticipantFailedWithMessage(){
        //Mock Data
        long mockId = 1L;

        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setName("xyz");
        participantDto.setAge(10);

        //Assert Throws
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> participantService.updateParticipant(mockId, participantDto));

        //Assert Message
        String actualMessage = resourceNotFoundException.getMessage();
        assertEquals("Participant id 1", actualMessage);
    }

    @Test
    void getParticipantById() throws ResourceNotFoundException {
        //Mock Data
        Long mockId = 1L;

        //Mock Repo
        Participant participantOutput = new Participant();
        participantOutput.setPartId(mockId);
        participantOutput.setName("xyz");
        participantOutput.setAge(10);

        when(participantRepository.findById(mockId)).thenReturn(java.util.Optional.of(participantOutput));

        //call
        ParticipantDto participantDtoResponse = participantService.getParticipantById(mockId);

        //Assert
        assertEquals(modelMapper.map(participantOutput, ParticipantDto.class), participantDtoResponse);
    }

    @Test
    void getParticipantByIdFailed() {
        //Mock Data
        Long mockId = 1L;

        //Assert
        assertThrows(ResourceNotFoundException.class, () -> participantService.getParticipantById(mockId));
    }

    @Test
    void deleteParticipantById() throws ResourceNotFoundException {
        //Mock Data
        Long mockId = 1L;

        //Mock Repo
        Participant participantOutput = new Participant();
        participantOutput.setPartId(mockId);
        participantOutput.setName("xyz");
        participantOutput.setAge(10);
        when(participantRepository.findById(mockId)).thenReturn(java.util.Optional.of(participantOutput));

        //call
        participantService.deleteParticipantById(mockId);

        //Assert, for void return , check if repository is called with delete
        verify(participantRepository).delete(participantOutput);
    }

    @Test
    void deleteParticipantByIdFailedWithMessage() {
        //Mock Data
        Long mockId = 1L;

        //Assert Throws
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> participantService.deleteParticipantById(mockId));

        //Assert Message
        String actualMessage = resourceNotFoundException.getMessage();
        assertEquals("Participant id 1", actualMessage);
    }
}