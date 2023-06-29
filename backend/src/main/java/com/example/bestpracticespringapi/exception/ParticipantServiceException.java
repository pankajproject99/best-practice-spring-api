package com.example.bestpracticespringapi.exception;

import java.io.Serial;

public class ParticipantServiceException extends Exception {
    @Serial
    private static final long serialVersionUID = -470180507998010368L;

    public ParticipantServiceException() {
        super();
    }

    public ParticipantServiceException(String message) {
        super(message);
    }
}
