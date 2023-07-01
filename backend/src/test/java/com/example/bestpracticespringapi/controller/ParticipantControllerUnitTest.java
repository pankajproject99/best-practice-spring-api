package com.example.bestpracticespringapi.controller;

import com.example.bestpracticespringapi.customAnnotation.UnitTest;
import com.example.bestpracticespringapi.dto.ApiResponse;
import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.service.ParticipantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@UnitTest
@ExtendWith(MockitoExtension.class) // important
@Tag("UnitTest") // Tagging to separate out test and run from run configuration
public class ParticipantControllerUnitTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @Test
    void getAllParticipantsSuccess() {
    //Unit test doesn't call the api it calls controller method and mocks the service

        // Mock Data
        List<ParticipantDto> participantDtoList = new ArrayList<>();
        ParticipantDto participantDto1 = new ParticipantDto(1L, "xyz", 5);
        ParticipantDto participantDto2 = new ParticipantDto(2L, "abc", 10);
        participantDtoList.add(participantDto1);
        participantDtoList.add(participantDto2);

        //Mock response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Participant Get List");
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setObj(participantDtoList);

        //Mock service
        when(participantService.getAllParticipants()).thenReturn(participantDtoList);

        //call
        ResponseEntity<ApiResponse> response = participantController.getAllParticipants();

        //Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(apiResponse, response.getBody());
    }
}
