package com.example.bestpracticespringapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private Boolean Success;
    private String message;
    private HttpStatus status;

    public ApiResponse(Boolean success, String message, HttpStatus status) {
        Success = success;
        this.message = message;
        this.status = status;
    }
}
