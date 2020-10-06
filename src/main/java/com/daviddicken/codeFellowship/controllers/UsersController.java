package com.daviddicken.codeFellowship.controllers;

import com.daviddicken.codeFellowship.models.users.Users;
import com.daviddicken.codeFellowship.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView addNewUser(String userName, String password){

        password = passwordEncoder.encode(password);
        Users newUser = new Users(userName, password);
        usersRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
