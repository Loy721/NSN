package com.example.nsn.service;

import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post update(Post post);
    void delete(PostPK postPk);
}
