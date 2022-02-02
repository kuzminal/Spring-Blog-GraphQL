package com.example.blog.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.blog.domain.Status;
import com.example.blog.domain.dto.AuthorInputRequest;
import com.example.blog.domain.dto.PostInputRequest;
import com.example.blog.domain.entity.Author;
import com.example.blog.domain.entity.Post;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MutationResolver implements GraphQLMutationResolver {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PostRepository postRepository;

    public Post newPost(PostInputRequest postInputRequest) {
        Author author = authorRepository.findById(postInputRequest.
                        getAuthorId())
                .orElse(new Author("Raj", 35));
        if (author.getId() == null)
            authorRepository.save(author);
        Post post = Post.builder()
                .title(postInputRequest.getTitle())
                .content(postInputRequest.getContent())
                .author(author)
                .status(Status.ACTIVE)
                .build();
        post = postRepository.save(post);
        return post;
    }

    public Author newAuthor(AuthorInputRequest authorInputRequest) {
        Author author = new Author(authorInputRequest.getName(),
                authorInputRequest.getAge());
        author = authorRepository.save(author);
        return author;
    }
}
