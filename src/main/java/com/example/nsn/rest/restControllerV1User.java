package com.example.nsn.rest;

import com.example.nsn.dto.UserDto;
import com.example.nsn.entity.Post;
import com.example.nsn.entity.User;
import com.example.nsn.security.UserDetailsImpl;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @GetMapping
    //@JsonView(Views.RoleUser.class)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{uid}")
    //@JsonView(Views.RoleUser.class)
    public User get(@PathVariable("uid") User user) {
        return user;
    }
    @GetMapping("{uid}/posts")
    //@JsonView(Views.RoleUser.class)
    public List<Post> getPosts(@PathVariable("uid") User user) {
        return user.getPosts();
    }

    @GetMapping("{surname}/{name}")
    public  List<User> getUsersByName(@PathVariable String surname,@PathVariable String name){
        List<User> list = userService.getAllByNameAndSurname(name,surname);
        return list;
    }

    @PostMapping
    //@JsonView(Views.RoleUser.class)
    public User create(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping("{uid}")
    //@JsonView(Views.RoleUser.class)
    public User update(@PathVariable("uid") String uid, //TODO: notFoundExp
            @RequestBody UserDto userDto) {
        userDto.setUid(uid);
        return userService.updateUser(userDto);
    }

    @GetMapping("{uid}/subscribers")
    public Set<User> getSubscriptions(@PathVariable("uid") User user){
        return user.getSubscribers();
    }



    @DeleteMapping("{uid}")
    //@JsonView(Views.RoleUser.class)
    public  void delete(@PathVariable("uid") User user){//TODO: notFoundExp
        userService.deleteUser(user);
    }

    @PostMapping("{uid}/follow")
    public void follow(@CurrentSecurityContext(expression = "authentication") Authentication auth,
                            @PathVariable("uid") User user){
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        if(principal.getUsername().equals(user.getUid())){
        User currentUser =  userService.getByUid(principal.getUsername());
        currentUser.getSubscriptions().add(user);
        userService.updateUser(currentUser);
        }
    }

    @PostMapping("{uid}/unfollow")
    public void unfollow(Principal principal,
                       @PathVariable("uid") User user){
        if(principal.getName().equals(user.getUid())){
            User currentUser =  userService.getByUid(principal.getName());
            user.getSubscribers().remove(currentUser);
            userService.updateUser(currentUser);
        }
    }
    //TODO: @hasAthority = ADMIN
    @PostMapping("{uid}/ban")
    public void ban(@PathVariable("uid") User user){
        user.setIsActive(false);
        userService.updateUser(user);
    }

    @PostMapping("{uid}/unban")
    public void unban(@PathVariable("uid") User user){
        user.setIsActive(true);
        userService.updateUser(user);
    }
}