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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //=================== Sign Up Mapping =================
    @PostMapping("/signup")
    public RedirectView addNewUser(String username, String password,
                                   String firstname, String lastname,
                                   String bio, Date dob, HttpServletRequest request) throws Exception {

       String passwordEncode = passwordEncoder.encode(password);

        Users newUser = new Users(username, passwordEncode, firstname, lastname, bio, dob);
        usersRepository.save(newUser);

        request.login(username, password); // auto login
        return new RedirectView("/myprofile");
    }

    //================== All Users mapping ================
    @GetMapping("/allUsers")
    public String allUsers(Model m, Principal principal){
        List<Users> allUsers = usersRepository.findAll();

        m.addAttribute("allUsers", allUsers);
        m.addAttribute("thisUser", principal.getName());
        return "allUsers";
    }

    //=================Add User ===============================
    @GetMapping("/addUser")
    public String addUser(){return "addUser";}

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //============== My Profile ===============================
    @GetMapping("/myprofile")
    public String profilePage(Principal principal, Model m){
        Users user = usersRepository.findByUserName(principal.getName());
        System.out.println("user " + user.getUsername());
        m.addAttribute("user", user);

        return "myprofile";
    }

    //================ User Info ===========================
    @GetMapping("/userInfo/{username}")
    public String userInfo(@PathVariable String username, Model m){
        Users user = usersRepository.findByUserName(username);
        user.getId();
        m.addAttribute("user", user);

        return "userpage";
    }

    //=============== Follow ===============================
    @PostMapping("/follow")
    public RedirectView RedirectView(Model m, Principal principal, String followed){
        Users potatoStalkers = usersRepository.findByUserName(principal.getName());
        Users potato = usersRepository.findByUserName(followed);
        System.out.println("potatoStalkers " + potatoStalkers);
        System.out.println("potato " + potato);

        potatoStalkers.followed.add(potato);
        potato.stalkers.add(potatoStalkers);

        return new RedirectView("/allUsers");
    }
}
