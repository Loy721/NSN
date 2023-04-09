package com.example.nsn.service.impl;

import com.example.nsn.entity.Post;
import com.example.nsn.repo.PostRepository;
import com.example.nsn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
