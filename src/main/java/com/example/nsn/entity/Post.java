package com.example.nsn.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @EmbeddedId
    private PostPK postPk;
    private String topic;
    private String content;
    @Column(columnDefinition = "CLOB")
    private String image;
    @ManyToOne
    @MapsId("uid")
    private User user;
}
