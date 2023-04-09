package com.example.nsn.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class PostPK implements Serializable {
    private LocalDateTime publication;
    private String uid;
}
