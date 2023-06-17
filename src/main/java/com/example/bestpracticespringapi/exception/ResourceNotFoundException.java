package com.example.bestpracticespringapi.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String objName, String objId, Long id) {
        super(objName + " " + objId + " " + id);
    }
}
