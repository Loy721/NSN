package com.example.nsn.controller;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Role;
import com.example.nsn.entity.User;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody UserDto userDto){
        User userFromDb = userService.getByEmail(userDto.getEmail());

        if(userFromDb != null)
            return "User Exist";//TODO:throw error

        userService.addUser(userDto);

        return "redirect:/login";
    }
}
