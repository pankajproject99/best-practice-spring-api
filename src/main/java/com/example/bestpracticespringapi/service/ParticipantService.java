package com.example.bestpracticespringapi.service;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.example.bestpracticespringapi.model.Participant;

import java.util.List;

public interface ParticipantService {
    //DTO not used
    // Later moved DTO from controller to Service, so service will receive and reply as DTO
    List<ParticipantDto> getAllParticipants();

    ParticipantDto createParticipant(ParticipantDto participantDto);

    ParticipantDto updateParticipant(long id, ParticipantDto participantDto) throws ResourceNotFoundException;

    ParticipantDto getParticipantById(Long id) throws ResourceNotFoundException;

    void deleteParticipantById(Long id) throws ResourceNotFoundException;

}
