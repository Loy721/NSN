package com.example.nsn.repo;

import com.example.nsn.entity.Post;
import com.example.nsn.entity.PostPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, PostPK> {
}
