package com.example.blog.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.blog.domain.entity.Author;
import com.example.blog.domain.entity.Post;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PostRepository postRepository;

    public List<Post> recentPosts(Integer limit, Integer offset, String
            orderBy) {
        log.info("recentPosts, params: {}, {}", limit, offset);
        PageRequest pageRequest = PageRequest.of(limit, offset, Sort.
                Direction.DESC, orderBy);
        return postRepository.findAll(pageRequest).getContent();
    }

    public List<Author> authorsWithTopPosts() {
        log.info("authorsWithTopPosts");
        return authorRepository.findAuthorsWithPosts();
    }
}
