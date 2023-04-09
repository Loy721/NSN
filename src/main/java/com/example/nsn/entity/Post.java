package com.example.nsn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @EmbeddedId
    private PostPK postPk;
    private String topic;
    private String content;
    @ManyToOne
    @MapsId("uid")
    private User user;
}
