package com.example.nsn.service;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.User;
import com.example.nsn.repo.UserRepository;

import java.util.List;


public interface UserService {
    List<User> getAll();
    User getByUid(String uid);
    User getByEmail(String email);
    User addUser(UserDto user);
    User updateUser(UserDto user);
    void deleteUser(String s);
    List<User> getAllBySurnameAndName(String name, String surname);
}
