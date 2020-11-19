package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PostController {


    @Autowired
    private PostRepository postRepository;


    @GetMapping("allPosts")
    public List<Post> getAllPosts(){
        return this.postRepository.findAll();
    }

    @GetMapping("postsByUser/{userId}")
    public List<Post> getPostByUser(@PathVariable("userId") long userId){
        return this.postRepository.getPostsByUserId(userId);
    }

    @GetMapping("postDetails/{postId}")
    public Post getPostById(@PathVariable("postId") long postId){
        return this.postRepository.getPostById(postId).get(0);
    }
}
