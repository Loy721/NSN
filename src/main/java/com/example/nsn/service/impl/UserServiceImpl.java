package com.example.nsn.service.impl;

import com.example.nsn.entity.User;
import com.example.nsn.repo.UserRepository;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getByUid(String uid) {
        return userRepository.findByUid(uid);
    }
}
