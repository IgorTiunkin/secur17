package com.example.secur17.controllers;

import com.example.secur17.dtos.PersonDTO;
import com.example.secur17.models.Person;
import com.example.secur17.services.PeopleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    public AuthController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("person", new PersonDTO());
        return "auth/signup";
    }

    @PostMapping("/saveUser")
    public String saveUser (@ModelAttribute("person") @Valid PersonDTO userDTO, BindingResult bindingResult) {
        System.out.println(userDTO.getPassword());
        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }
        Person user = convertToPerson(userDTO);
        peopleService.saveUser(user);
        return "redirect:/auth/login";
    }
    @GetMapping("/hello")
    public String hello() {
        return "auth/hello";
    }

    private Person convertToPerson(PersonDTO userDTO) {
        Person user = modelMapper.map(userDTO, Person.class);
        user.setRole("ROLE_USER");
        return user;
    }
}
