package com.example.nsn.service.impl;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.User;
import com.example.nsn.mapper.UserMapper;
import com.example.nsn.repo.UserRepository;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public User addUser(UserDto userDto) {
        User user = UserMapper.UserDtoToUser(userDto);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = UserMapper.UserDtoToUser(userDto);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public List<User> getAllBySurnameAndName(@RequestParam String name,@RequestParam String surname) {
        return userRepository.findAllByNameAndSurname(name,surname);
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
