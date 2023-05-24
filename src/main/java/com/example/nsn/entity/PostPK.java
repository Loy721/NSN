package com.example.nsn.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostPK implements Serializable  {
    private String uid;
    private LocalDateTime publication;
}
