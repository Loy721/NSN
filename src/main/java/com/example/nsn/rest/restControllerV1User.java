package com.example.nsn.rest;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.User;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class restControllerV1User {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/update")
    public User update(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }
}
