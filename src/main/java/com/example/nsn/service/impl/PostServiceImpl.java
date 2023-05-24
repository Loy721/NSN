package com.example.nsn.service.impl;

import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;
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

    @Override
    public Post create(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Post get(PostPK postPK) {
        return postRepository.findByPostPk(postPK);
    }

    @Override
    public Post edit(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public void delete(PostPK postPk) {
        postRepository.deleteById(postPk);
    }
}
