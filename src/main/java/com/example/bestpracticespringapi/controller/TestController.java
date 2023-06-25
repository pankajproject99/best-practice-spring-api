package com.example.bestpracticespringapi.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test") // use plural names
@Profile("test")
public class TestController {

    @GetMapping("/exception-endpoint")
    public void throwGenericException() throws Exception {
        throw new Exception("Something went wrong");
    }
}
