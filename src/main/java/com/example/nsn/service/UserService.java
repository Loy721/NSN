package com.example.nsn.service;

import com.example.nsn.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAll();

    Boolean isExistByEmail(String email);

    User getByUid(String uid);

    User getByEmail(String email);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    List<User> getAllByNameAndSurname(String name, String surname);
}
