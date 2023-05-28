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

    public static UserDto userToUserDto(User user) {
        return new UserDto(user.getUid(), user.getName(), user.getSurname(),
                user.getEmail(), user.getPassword(),user.getPhoto(), user.getBirthday());
    }

    public static User userDtoToNewUser(UserDto user) {
            user.setUid((UUID.randomUUID()).toString());
        return User.builder().uid(user.getUid()).name(user.getName())
                .surname(user.getSurname()).email(user.getEmail())
                .password(user.getPassword()).photo(user.getPhoto())
                .birthday(user.getBirthday()).role(Set.of(Role.USER))
                .isActive(true).build();
    }

    public static User userDtoToExistUser(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setPhoto(userDto.getPhoto());
        return user;
    }
}
