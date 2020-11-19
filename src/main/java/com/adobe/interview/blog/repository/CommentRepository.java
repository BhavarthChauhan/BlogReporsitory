package com.adobe.interview.blog.repository;

import com.adobe.interview.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {



    @Query(value="Select c from Comment c where c.post.id = :id")
    public List<Comment> getCommentByPostId(@Param("id") long id);


}
