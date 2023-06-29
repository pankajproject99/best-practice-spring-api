package com.example.bestpracticespringapi.controller;

import com.example.bestpracticespringapi.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        //Fixed: Model is required only if we are using Themleaf login page where Im defining userDto Object
        // For without Thmelaf Model is not required during landing page of /login
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView validateLoginPage(@ModelAttribute("user") UserDto userDto, Model model,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes) {
        log.info("Post Login : " + userDto.toString());


        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        modelAndView.setViewName("home");
//        Fixed: User redireect instead of setting home as wanted to /home in url link
        redirectAttributes.addFlashAttribute("user", userDto);
//        modelAndView.addObject("userDto", userDto);
//        Fixed: Due to redirect it was not passing values to GET:/home

        return modelAndView;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView showHomePage(@ModelAttribute("user") UserDto userDto, Model model){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }
}
