package com.adobe.interview.blog.repository;

import com.adobe.interview.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value="Select p FROM Post p where p.user.id = :user_id")
    public List<Post> getPostsByUserId(@Param("user_id") long user_id);

    @Query(value="Select p FROM Post p where p.id = :id")
    public List<Post> getPostById(@Param("id") long id);

    @Query(value = "Select p FROM Post p where p.blogSpace.id = :id")
    public List<Post> getPostByBlogSpace(@Param("id") long id);

}
