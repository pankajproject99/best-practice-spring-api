package com.example.bestpracticespringapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Boolean Success;
    private String message;
    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String requestedURI;
    @JsonInclude(JsonInclude.Include.NON_EMPTY) //only show when it's not empty
    private Object obj;

    public ApiResponse(Boolean success, String message, HttpStatus status) {
        Success = success;
        this.message = message;
        this.status = status;
    }

    public ApiResponse(Boolean success, String message, HttpStatus status, Object obj) {
        Success = success;
        this.message = message;
        this.status = status;
        this.obj = obj;
    }
}
