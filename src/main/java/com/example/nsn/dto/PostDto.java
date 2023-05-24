package com.example.nsn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private LocalDateTime publication;
    private String topic;
    private String content;
    private String image;
}
