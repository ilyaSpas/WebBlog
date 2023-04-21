package com.ilsy.school_dance.controllers;

import com.ilsy.school_dance.models.User;
import com.ilsy.school_dance.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    public String userRegistration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String userAddRegistration(@RequestParam String login,
                                      @RequestParam String pass,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName,
                                      @RequestParam String fatherName,
                                      @RequestParam String birthday,
                                      @RequestParam String number,
                              Model model) {
        User user = new User(login, pass, firstName, lastName, fatherName,birthday, number);
        userRepository.save(user);
        return "redirect:/";
    }
}
