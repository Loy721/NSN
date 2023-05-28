package com.example.nsn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {
    private final String accessToken;
    private final String uid;
    private final List<String> roles;
}
