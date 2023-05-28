package com.example.nsn.service.impl;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Post;
import com.example.nsn.entity.User;
import com.example.nsn.execption.UserNotFoundException;
import com.example.nsn.mapper.UserMapper;
import com.example.nsn.repo.UserRepository;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllByNameAndSurname(String name, String surname) {
        return userRepository.findAllByNameAndSurname(name, surname);
    }

    @Override
    public Boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException("User with '" + email + "' email not found");
        return user;
    }

    @Override
    public User getByUid(String uid) {
        User user = userRepository.findByUid(uid);
        if (user == null)
            throw new UserNotFoundException("User with '" + uid + "' uid not found");
        return user;
    }
}
