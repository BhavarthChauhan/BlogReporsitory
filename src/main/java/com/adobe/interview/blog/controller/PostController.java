package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private BlogSpaceRepository blogSpaceRepository;

    /**
     * Gets all the posts in the blogSpace
     * @param blogSpaceId id of the blogSpace
     * @return
     */
    @GetMapping("allPostsByBlogSpace/{blogSpaceId}")
    public ResponseEntity getAllPosts(@PathVariable("blogSpaceId") long blogSpaceId) {
        try {
            NewPostResponseDTO newPostResponseDTO = this.postService.getAllPostsBySpace(blogSpaceId, blogSpaceRepository, postRepository);
            return new ResponseEntity(newPostResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Gets all the posts by user
     * @param userId userId of the user
     * @return
     */
    @GetMapping("postsByUser/{userId}")
    public ResponseEntity getPostByUser(@PathVariable("userId") long userId) {
        try {
            List<Post> newPostResponseDTO = this.postService.getPostsByUser(userId, postRepository);
            return new ResponseEntity(newPostResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Gets blog post details for the post, the title, description, content
     * @param postId id of the post
     * @return
     */
    @GetMapping("postDetails/{postId}")
    public ResponseEntity getPostById(@PathVariable("postId") long postId) {
        try {
            Post newPostResponseDTO = this.postService.getPostDetails(postRepository, postId);
            return new ResponseEntity(newPostResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Saves a post added by the user
     * @param newPost post object with post details
     * @return
     */
    @PostMapping("addPost")
    public ResponseEntity addNewPost(@RequestBody NewPostDTO newPost) {
        try {
            NewPostResponseDTO newPostResponseDTO = this.postService.addNewPost(newPost, blogSpaceRepository, postRepository);
            return new ResponseEntity(newPostResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
