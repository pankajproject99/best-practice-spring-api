package com.example.bestpracticespringapi.exception;

import com.example.bestpracticespringapi.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception,
                                                                                         final HttpServletRequest request){
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            errors.add(fieldError.getField() + " -> " + fieldError.getDefaultMessage());
        }

        ApiResponse error = new ApiResponse();
        error.setSuccess(false);
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("handleMethodArgumentNotValidException" + " " + errors);
        error.setRequestedURI(request.getRequestURI());

        return error;
    }

    //Response<Entity> with @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleHttpMessageNotReadableExceptionWithSpringAnnotationResponse(final HttpMessageNotReadableException exception,
                                                              final HttpServletRequest request){
        ApiResponse error = new ApiResponse();
        error.setSuccess(false);
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("httpMessageNotReadableException" + " " + exception.getMessage() + " " + request.getLocalPort());
        error.setRequestedURI(request.getRequestURI());

        return error;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse> handleResourceNotFound(final ResourceNotFoundException exception,
                                                             final HttpServletRequest request){
        ApiResponse error = new ApiResponse();
        error.setSuccess(false);
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage("handleResourceNotFound" + " " + exception.getMessage() + " " + request.getLocalPort());
        error.setRequestedURI(request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(final Exception exception,
                                                              final HttpServletRequest request){
        ApiResponse error = new ApiResponse();
        error.setSuccess(false);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage("handleGenericException" + " " + exception.getMessage());
        error.setRequestedURI(request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
