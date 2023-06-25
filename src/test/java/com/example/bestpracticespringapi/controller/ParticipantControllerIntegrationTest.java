package com.example.bestpracticespringapi.controller;

import com.example.bestpracticespringapi.customAnnotation.IntegrationTest;
import com.example.bestpracticespringapi.dto.ApiResponse;
import com.example.bestpracticespringapi.dto.ParticipantDto;
import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.example.bestpracticespringapi.service.ParticipantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest //Provides Spring Boot Context
//@WebMvcTest(controllers = ParticipantController.class) // can also be used instead of Auto wiring controller.
// Also, we are restricting controller to narrow down application context
@AutoConfigureMockMvc
@IntegrationTest
class ParticipantControllerIntegrationTest {

    @Autowired
    ObjectMapper objectMapper; // beans automatically provided by Spring boot for mapping Json

    @MockBean   //Since we don't want to test service bean, MockBean replaces bean of same type from context with Mockito mock
    ParticipantService participantService;

    @Autowired
    private ParticipantController participantController;

    @Autowired
    private MockMvc mockMvc; // Moc Http

    @Test
    void getAllParticipantsSuccess() throws Exception {
        MediaType responseContentType = new MediaType(MediaType.APPLICATION_JSON);
        // Using String and not showing obj as service is not mocked
        String responseBody = "{\"message\":\"Participant Get List\",\"status\":\"OK\",\"success\":true}";

        //Test verifies Controller respond to certain URL
        // Test verifies correct HTTP GET
        mockMvc.perform(MockMvcRequestBuilders.get("/api/participants")
                        .content(responseBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(responseContentType))
                .andExpect(MockMvcResultMatchers.content().json(responseBody))
        ;
    }

    @Test
    void createParticipant() throws Exception {
        ParticipantDto participantDtoRequest = new ParticipantDto();
        participantDtoRequest.setName("mmm");
        participantDtoRequest.setAge(200);

        MediaType responseContentType = new MediaType(MediaType.APPLICATION_JSON);
        ParticipantDto participantDtoResponse = new ParticipantDto();
        participantDtoResponse.setId(1L);
        participantDtoResponse.setName("mmm");
        participantDtoResponse.setAge(200);

        when(participantService.createParticipant(participantDtoRequest)).thenReturn(participantDtoResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantDtoRequest)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(responseContentType))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(participantDtoResponse)));

        //2nd Way Break in 2 parts. Gives more options
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantDtoRequest)))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(participantDtoResponse));

    }

    @Test
    void createParticipantFieldValidationFailed() throws Exception {
        // Missing mandatory name
        ParticipantDto participantDtoRequest = new ParticipantDto();
        participantDtoRequest.setAge(200);

        MediaType responseContentType = new MediaType(MediaType.APPLICATION_JSON);

        //Test status and save result
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantDtoRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("handleMethodArgumentNotValidException [name -> Name is mandatory]");
        apiResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setRequestedURI("/api/participants");
        apiResponse.setSuccess(false);
        String expectedResponseBody = objectMapper.writeValueAsString(apiResponse);

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);

    }

    @Test
    void getParticipantById() throws Exception {

        //Mock service
        ParticipantDto participantDto = new ParticipantDto(1L, "xyz", 10);
        when(participantService.getParticipantById(1L)).thenReturn(participantDto);

        //Call
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/participants/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(participantDto)))
                .andReturn();

        //Assert N/A as Checked above
    }

    @Test
    void updateParticipantSuccess() throws Exception {

//        Using object as Object Mapper will be used for JSON to Object conversion
        ParticipantDto participantDtoRequest = new ParticipantDto();
        participantDtoRequest.setName("pan");
        participantDtoRequest.setAge(12);

        MediaType responseContentType = new MediaType(MediaType.APPLICATION_JSON);
        ParticipantDto participantDtoResponse = new ParticipantDto();
        participantDtoResponse.setId(1L);
        participantDtoResponse.setName("san");
        participantDtoResponse.setAge(13);

        //Mock Service
        when(participantService.updateParticipant(1L, participantDtoRequest)).thenReturn(participantDtoResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/participants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantDtoRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(participantDtoResponse)))
                .andExpect(MockMvcResultMatchers.content().contentType(responseContentType));

    }

    @Test
    void updateParticipantNotFound() throws Exception {
//        Using object as Object Mapper will be used for JSON to Object conversion
        ParticipantDto participantDtoRequest = new ParticipantDto();
        participantDtoRequest.setName("pan");
        participantDtoRequest.setAge(12);

        MediaType responseContentType = new MediaType(MediaType.APPLICATION_JSON);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("handleResourceNotFound Participant id 1 80");
        apiResponse.setStatus(HttpStatus.NOT_FOUND);
        apiResponse.setRequestedURI("/api/participants/1");
        apiResponse.setSuccess(false);

        //Mock Service, instead of object test Throw Exception
        when(participantService.updateParticipant(1L, participantDtoRequest))
                .thenThrow(new ResourceNotFoundException("Participant", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/participants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participantDtoRequest)))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(apiResponse)))
                .andExpect(MockMvcResultMatchers.content().contentType(responseContentType));
    }

    @Test
    void deleteParticipant() throws Exception {
        //Call
        ApiResponse apiResponse = new ApiResponse(true, "Participant deleted successfully", HttpStatus.OK);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/participants/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(apiResponse)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}