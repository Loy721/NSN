package com.example.nsn.service;

import com.example.nsn.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAll();
    User getByUid(String uid);
    User getByEmail(String email);

}
