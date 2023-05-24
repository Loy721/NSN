package com.example.nsn.security;

import com.example.nsn.entity.User;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found"+email);//TODO: return req body
        return new UserDetailsImpl(user.getUid(),user.getPassword(),user.getIsActive(),user.getRole());
    }
}
