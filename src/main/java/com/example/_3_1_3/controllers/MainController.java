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
    @GetMapping("/read_profile")
    public String pageForReadProfile(Principal principal){
        return "read profile page";
    }
    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins(Principal principal){
        return "admins page";
    }
}
