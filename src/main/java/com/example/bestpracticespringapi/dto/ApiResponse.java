package com.example.bestpracticespringapi.dto;

import com.example.bestpracticespringapi.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Boolean Success;
    private String message;
    private HttpStatus status;
    private String requestedURI;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) //only show when it's not empty
    private Object obj;

    public ApiResponse(Boolean success, String message, HttpStatus status) {
        Success = success;
        this.message = message;
        this.status = status;
    }
}
