package com.example.bestpracticespringapi.dto;

import io.cucumber.messages.internal.com.google.protobuf.Api;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testEquals() {
        ApiResponse apiResponse1 = new ApiResponse(true,"dummy message", HttpStatus.OK);
        ApiResponse apiResponse2 = new ApiResponse(true,"dummy message", HttpStatus.OK);

        //Assert
        assertThat(apiResponse1).isEqualTo(apiResponse2);
    }

    @Test
    void testHashCode() {
        ApiResponse apiResponse1 = new ApiResponse(true,"dummy message", HttpStatus.OK);
        ApiResponse apiResponse2 = new ApiResponse(true,"dummy message", HttpStatus.OK);

        //Assert
        assertThat(apiResponse1.hashCode()).isEqualTo(apiResponse2.hashCode());
    }

    @Test
    void testToString() {
        ApiResponse apiResponse1 = new ApiResponse(true,"dummy message", HttpStatus.OK);

        //Assert
        assertThat(apiResponse1.toString()).contains("Success=true", "message=dummy message");
    }
}