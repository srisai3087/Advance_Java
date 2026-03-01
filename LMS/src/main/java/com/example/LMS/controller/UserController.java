package com.example.LMS.controller;

import com.example.LMS.service.UserService;
import org.springframework.stereotype.Controller;



import com.example.LMS.model.User;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/register")
    public String showRegister(Model model){

        model.addAttribute("user", new User());

        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        service.registerUser(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model){
        if(service.login(email,password)){
            return "dashboard";
        }
        model.addAttribute("error","Invalid credentials");
        return "login";
    }
}
