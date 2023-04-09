package com.example.nsn.rest;


import com.example.nsn.entity.Post;
import com.example.nsn.service.PostService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class restControllerV1Post {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<Post> getAll(){
        return postService.getAll();
    }
}
