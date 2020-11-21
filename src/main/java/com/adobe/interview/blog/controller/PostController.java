package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class PostController {

    PostService postService = new PostService();

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogSpaceRepository blogSpaceRepository;

    @GetMapping("allPostsByBlogSpace/{blogSpaceId}")
    public NewPostResponseDTO getAllPosts(@PathVariable("blogSpaceId") long blogSpaceId)
    {
        Optional<BlogSpace> blogSpaceOptional = this.blogSpaceRepository.findById(blogSpaceId);
        if(blogSpaceOptional.isPresent()){
            BlogSpace blogSpace = blogSpaceOptional.get();
            return this.postService.getPosts(this.postRepository.getPostByBlogSpace(blogSpaceId), blogSpace.getUser(), blogSpace);
        }
        BlogSpace blogSpace = this.blogSpaceRepository.getBlogSpaceById(blogSpaceId).get(0);
        return new NewPostResponseDTO();

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
    public NewPostResponseDTO addNewPost(@RequestBody NewPostDTO newPost){
        User user = this.userRepository.getUserByUserName(newPost.getUserName()).get(0);
        BlogSpace blogSpace = this.blogSpaceRepository.getBlogSpaceById(newPost.getBlogSpaceId()).get(0);
        Optional<BlogSpace> blogSpaceOptional = this.blogSpaceRepository.findById(newPost.getBlogSpaceId());
        if(blogSpaceOptional.isPresent()){
            BlogSpace blogSpace1 = blogSpaceOptional.get();
            Post post = new Post(newPost.getTitle(), newPost.getDescription(), newPost.getContent(), user,blogSpace1);
            this.postRepository.save(post);
            return this.postService.getPosts(this.postRepository.getPostByBlogSpace(blogSpace1.getId()), user, blogSpace1);
        }else{
            return new NewPostResponseDTO();
        }
    }
}
