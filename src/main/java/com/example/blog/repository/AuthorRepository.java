package com.example.blog.repository;

import com.example.blog.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("Select distinct a from Author a left join fetch a.posts")
    List<Author> findAuthorsWithPosts();
}
