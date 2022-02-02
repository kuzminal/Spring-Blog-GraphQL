package com.example.blog.domain.dto;

import lombok.Data;

@Data
public class PostInputRequest {
    String title;
    String content;
    Long authorId;
}
