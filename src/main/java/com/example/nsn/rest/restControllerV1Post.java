package com.example.nsn.rest;

import com.example.nsn.dto.PostDto;
import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;
import com.example.nsn.entity.User;
import com.example.nsn.service.PostService;
import com.example.nsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@CrossOrigin("http://localhost:8081/")
public class restControllerV1Post {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Post> getAll() {
        List<Post> posts = postService.getAll();
        return posts;
    }

    @GetMapping("{uid}/{date}")
    public Post getPost(@PathVariable String uid,
                        @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        Post post = postService.get(new PostPK(uid, date));
        return post;
    }

    @PostMapping
    public Post create(Principal principal, // principal.getName() == user.uid
                       @RequestBody PostDto postDto) {
            User user = userService.getByUid(principal.getName());
            return postService.create(new Post(new PostPK(principal.getName(),
                                            postDto.getPublication()), postDto.getTopic(),
                                            postDto.getContent(), postDto.getImage(), user));
    }

    @DeleteMapping("{uid}/{date}")
    public void delete(@PathVariable String uid,
                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {//TODO: notFoundExp
        postService.delete(new PostPK(uid, date));
    }
}
