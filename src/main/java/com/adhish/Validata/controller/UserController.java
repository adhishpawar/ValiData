package com.adhish.Validata.controller;

import com.adhish.Validata.service.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserCheckService service;

    // Check if username is available
    @GetMapping("/check")
    public String check(@RequestBody String username) {
        return service.checkUsername(username);
    }

    // Register a new username
    @PostMapping("/register")
    public String register(@RequestBody String username) {
        if(service.registerUsername(username)){
            return "Username registered successfully ✅";
        }else {
            return "Username is not available";
        }

    }
}
