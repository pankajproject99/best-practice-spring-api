package com.example.bestpracticespringapi.exception;

public class ParticipantServiceException extends Exception {
    private static final long serialVersionUID = -470180507998010368L;

    public ParticipantServiceException() {
        super();
    }

    public ParticipantServiceException(String message) {
        super(message);
    }
}
