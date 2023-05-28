package com.example.nsn.rest;

import com.example.nsn.dto.PostDto;
import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;
import com.example.nsn.entity.User;
import com.example.nsn.service.PostService;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@CrossOrigin("http://localhost:8081/")
public class restControllerV1Post {

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public restControllerV1Post(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("{uid}/{date}")
    public Post getPost(@PathVariable String uid,
                        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return postService.get(new PostPK(uid, date));
    }

    @PostMapping
    public Post create(Principal principal,
                       @RequestBody PostDto postDto) {
        User user = userService.getByUid(principal.getName());
        return postService.create(new Post(new PostPK(principal.getName(),
                postDto.getPublication()), postDto.getTopic(),
                postDto.getContent(), postDto.getImage(), user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #uid == authentication.principal.username")
    @DeleteMapping("{uid}/{date}")
    public void delete(@PathVariable String uid,
                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        postService.delete(new PostPK(uid, date));
    }
}
