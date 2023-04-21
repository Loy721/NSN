package com.example.nsn.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String uid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate birthday;
}
