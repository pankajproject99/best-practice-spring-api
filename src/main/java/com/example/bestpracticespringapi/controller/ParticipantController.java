package com.example.bestpracticespringapi.controller;

import com.example.bestpracticespringapi.dto.ApiResponse;
import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.example.bestpracticespringapi.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants") // use plural names
public class ParticipantController {

    ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllParticipants(){
        List<ParticipantDto> participantDtoList = participantService.getAllParticipants();
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Participant Get List", HttpStatus.OK, participantDtoList);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        return ResponseEntity.ok().body(participantService.getAllParticipants());
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> createParticipant(@RequestBody ParticipantDto participantDto){
        ParticipantDto participantDtoResp = participantService.createParticipant(participantDto);
        return new ResponseEntity<>(participantDtoResp, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getParticipantById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(@PathVariable long id, @Valid @RequestBody ParticipantDto participantDto) throws ResourceNotFoundException {
        ParticipantDto participantDtoResponse = participantService.updateParticipant(id, participantDto);
        return ResponseEntity.ok().body(participantDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteParticipant(@PathVariable long id) throws ResourceNotFoundException {
        participantService.deleteParticipantById(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Participant deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}