package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.comment.PostedComment;
import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class CommentController {


    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("commentsForPost/{postId}")
    public List<Comment> getCommentsForPost(@PathVariable("postId") long postId) {
        return this.commentRepository.getCommentByPostId(postId);
    }

    @PostMapping("addComment")
    public List<Comment> addCommentToPost(@RequestBody PostedComment postedComment) {

        System.out.println("asd");

        Post post = this.postRepository.getPostById(postedComment.getPostId()).get(0);
        User user = this.userRepository.getUserByUserName(postedComment.getUserName()).get(0);

        Comment comment = new Comment(postedComment.getText(), user, post);
        this.commentRepository.save(comment);
        return this.commentRepository.getCommentByPostId(postedComment.getPostId());
    }
}
