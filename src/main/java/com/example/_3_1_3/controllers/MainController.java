package com.example._3_1_3.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/authenticated")
    public String pageForAuthenticatedUser(Principal principal){
        return "Soobshenyi s metoda pageForAuthenticatedUser 16 stroka page controllers :" +  principal.getName();
    }
}
