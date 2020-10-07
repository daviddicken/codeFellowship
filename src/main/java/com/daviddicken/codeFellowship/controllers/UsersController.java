package com.daviddicken.codeFellowship.controllers;

import com.daviddicken.codeFellowship.models.users.Users;
import com.daviddicken.codeFellowship.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;

@Controller
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView addNewUser(String username, String password,
                                   String firstname, String lastname,
                                   String bio, Date dob){

        password = passwordEncoder.encode(password);

        Users newUser = new Users(username, password, firstname, lastname, bio, dob);
        usersRepository.save(newUser);

        return new RedirectView("/userInfo/" + username );
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // Huge help from Jack in a lot of places in this app but refactoring this function was one of them
    @GetMapping("/verified")
    public RedirectView RedirectView(Principal principal){
        return new RedirectView("/userInfo/" + principal.getName() );
    }

    @GetMapping("/userInfo/{username}")
    public String userInfo(@PathVariable String username, Model m){
        Users user = usersRepository.findByUserName(username);
        m.addAttribute("user", user);

        return "userpage";
    }
}
