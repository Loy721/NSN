package com.example.nsn.mapper;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Role;
import com.example.nsn.entity.User;

import java.util.Collections;
import java.util.Set;

public class UserMapper {

    public static UserDto UserToUserDto(User user) {
        return new UserDto(user.getUid(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(), user.getBirthday());
    }

    public static User UserDtoToUser(UserDto user) {
        return new User(user.getUid(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(), true, user.getBirthday(),Set.of(Role.USER),
                null, null, null);
    }

}
