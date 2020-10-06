package com.daviddicken.codeFellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")

    public String showHome(){
        return "home";
    }


}
