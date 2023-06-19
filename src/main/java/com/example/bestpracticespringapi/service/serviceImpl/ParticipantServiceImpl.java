package com.example.bestpracticespringapi.service.serviceImpl;

import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.example.bestpracticespringapi.model.Participant;
import com.example.bestpracticespringapi.repository.ParticipantRepository;
import com.example.bestpracticespringapi.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Service annotation to be used in implementation if present otherwise use in interface
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    ModelMapper modelMapper;

    private final ParticipantRepository participantRepository; //Autowired not required instead use at constructor, helpful in test mock

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = modelMapper.map(participantDto, Participant.class);
        Participant part = participantRepository.save(participant);

        return modelMapper.map(part, ParticipantDto.class);
    }

    @Override
    public ParticipantDto updateParticipant(long id, ParticipantDto participantDto) throws ResourceNotFoundException{
        Participant part = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant", "id", id));
        part.setName(participantDto.getName());
        part.setAge(participantDto.getAge());

        Participant participantResponse = participantRepository.save(part);
        return modelMapper.map(participantResponse, ParticipantDto.class);
    }

    @Override
    public ParticipantDto getParticipantById(Long id) throws ResourceNotFoundException {
        Optional<Participant> part = participantRepository.findById(id);
        if(part.isPresent()){
            return modelMapper.map(part.get(), ParticipantDto.class);
        } else {
            throw new ResourceNotFoundException("Participant", "id", id);
        }
    }

    @Override
    public void deleteParticipantById(Long id) throws ResourceNotFoundException {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant", "id", id));

        participantRepository.delete(participant);
    }
}
