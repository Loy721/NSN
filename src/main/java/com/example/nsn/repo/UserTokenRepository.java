package com.example.nsn.repo;

import com.example.nsn.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    List<UserToken> findAllByUid(String uid);

    UserToken findByToken(String token);

    void deleteByToken(String token);
}