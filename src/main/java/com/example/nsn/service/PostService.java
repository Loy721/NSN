package com.example.nsn.service;

import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;

import java.util.List;

public interface PostService {
    List<Post> getAll();
    Post get(PostPK postPK);
    Post edit(Post post);
    void delete(PostPK postPk);
    Post create(Post post);
}
