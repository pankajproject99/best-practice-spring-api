package com.example.bestpracticespringapi.controller;

import com.example.bestpracticespringapi.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView validateLoginPage(@ModelAttribute UserDto userDto, Model model) {
        log.info("Post Login : " + userDto.toString());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", userDto);

        return modelAndView;
    }
}
