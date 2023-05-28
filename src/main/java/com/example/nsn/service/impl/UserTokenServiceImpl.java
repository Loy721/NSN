package com.example.nsn.service.impl;

import com.example.nsn.entity.UserToken;
import com.example.nsn.repo.UserTokenRepository;
import com.example.nsn.service.UserTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public UserTokenServiceImpl(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public List<UserToken> getByUid(String uid) {
        return userTokenRepository.findAllByUid(uid);
    }

    @Override
    @Transactional
    public void deleteByToken(String token) {
        userTokenRepository.deleteByToken(token);
    }

    @Override
    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    @Override
    public UserToken getByToken(String token) {
        return userTokenRepository.findByToken(token);
    }
}
