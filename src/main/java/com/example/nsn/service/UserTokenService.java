package com.example.nsn.service;

import com.example.nsn.entity.UserToken;

import java.util.List;

public interface UserTokenService {
    List<UserToken> getByUid(String uid);

    UserToken getByToken(String uid);

    void deleteByToken(String token);

    UserToken save(UserToken userToken);
}
