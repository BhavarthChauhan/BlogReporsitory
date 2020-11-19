package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.post.NewPost;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PostController {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("addPost")
    public List<Post> addNewPost(@RequestBody NewPost newPost){
        User user = this.userRepository.getUserByUserName(newPost.getUserName()).get(0);
        Post post = new Post(newPost.getTitle(), newPost.getDescription(), newPost.getContent(), user);
        this.postRepository.save(post);
        return this.postRepository.findAll();
    }
}
