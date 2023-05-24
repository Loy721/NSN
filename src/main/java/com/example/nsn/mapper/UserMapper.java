package com.example.nsn.mapper;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Role;
import com.example.nsn.entity.User;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UserMapper {

    public static UserDto UserToUserDto(User user) {
        return new UserDto(user.getUid(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(),user.getPhoto(), user.getBirthday());
    }

    public static User UserDtoToUser(UserDto user) {
        if(user.getUid()==null)
            user.setUid((UUID.randomUUID()).toString());
        return new User(user.getUid(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(),user.getPhoto(), true, user.getBirthday(),Set.of(Role.USER),
                null, null, null);
    }

}
