package com.example.nsn.rest;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Post;
import com.example.nsn.entity.User;
import com.example.nsn.mapper.UserMapper;
import com.example.nsn.security.UserDetailsImpl;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("http://localhost:8081/")
public class restControllerV1User {
    private final UserService userService;

    @Autowired
    public restControllerV1User(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{uid}")
    public User get(@PathVariable("uid") User user) {
        return user;
    }

    @GetMapping("{uid}/posts")
    public List<Post> getPosts(@PathVariable("uid") User user) {
        return user.getPosts();
    }

    @GetMapping("{surname}/{name}")
    public List<User> getUsersByName(@PathVariable String surname, @PathVariable String name) {
        return userService.getAllByNameAndSurname(name, surname);
    }

    @PreAuthorize("#uid == authentication.principal.username")
    @PutMapping("{uid}")
    public User update(@PathVariable("uid") String uid,
                       @RequestBody UserDto userDto) {
        User user = userService.getByUid(uid);
        return userService.updateUser(UserMapper.userDtoToExistUser(userDto, user));
    }

    @GetMapping("{uid}/subscribers")
    public Set<User> getSubscriptions(@PathVariable("uid") User user) {
        return user.getSubscribers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{uid}")
    public void delete(@PathVariable("uid") User user) {
        userService.deleteUser(user);
    }

    @PostMapping("{uid}/follow")
    public void follow(@CurrentSecurityContext(expression = "authentication") Authentication auth,
                       @PathVariable("uid") User user) {
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        if (!principal.getUsername().equals(user.getUid())) {
            User currentUser = userService.getByUid(principal.getUsername());
            currentUser.getSubscriptions().add(user);
            userService.updateUser(currentUser);
        }
    }

    @PostMapping("{uid}/unfollow")
    public void unfollow(Principal principal,
                         @PathVariable("uid") User user) {
        if (!principal.getName().equals(user.getUid())) {
            User currentUser = userService.getByUid(principal.getName());
            user.getSubscribers().remove(currentUser);
            userService.updateUser(currentUser);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("{uid}/ban")
    public void ban(@PathVariable("uid") User user) {
        user.setIsActive(false);
        userService.updateUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("{uid}/unban")
    public void unban(@PathVariable("uid") User user) {
        user.setIsActive(true);
        userService.updateUser(user);
    }
}