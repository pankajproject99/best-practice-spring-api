package com.example.bestpracticespringapi.exception;

import com.example.bestpracticespringapi.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // added so that test controller can be used.
class ExceptionHandlerControllerAdviceTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

//    private class TestController {
//        @GetMapping("/exception-endpoint")
//        public void throwGenericException() throws Exception{
//            throw new Exception("Something went wrong");
//        }
//    }

    @Test
    void handleMethodArgumentNotValidException() {
    }

    @Test
    void handleHttpMessageNotReadableExceptionWithSpringAnnotationResponse() {
    }

    @Test
    void handleResourceNotFound() {
    }

    @Test
    void handleGenericException() throws Exception {
//        ExceptionHandlerControllerAdvice advice = new ExceptionHandlerControllerAdvice();
//        mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
//                .setControllerAdvice(advice)
//                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/test/exception-endpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Assert Status
        int responseStatus = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseStatus);

        //Assert Complete response
        ApiResponse apiResponseExpected = new ApiResponse();
        apiResponseExpected.setSuccess(false);
        apiResponseExpected.setMessage("handleGenericException Something went wrong");
        apiResponseExpected.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiResponseExpected.setRequestedURI("/api/test/exception-endpoint");
        apiResponseExpected.setObj(null);

        ApiResponse apiResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ApiResponse.class);
        assertEquals(apiResponseExpected,apiResponse);
    }
}