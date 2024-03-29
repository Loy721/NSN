package com.example.nsn.security;

import com.example.nsn.entity.User;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) {
        User user = userService.getByUid(uid);

        return new UserDetailsImpl(user.getUid(), user.getPassword(), user.getIsActive(), user.getRole());
    }
}
