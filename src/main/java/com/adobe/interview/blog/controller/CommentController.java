package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.comment.CommentResponseDTO;
import com.adobe.interview.blog.components.comment.PostedCommentDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class CommentController {

    CommentService commentService = new CommentService();

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("commentsForPost/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsForPost(@PathVariable("postId") long postId) {
        return new ResponseEntity<List<CommentResponseDTO>>(this.commentService.getCommentsForPost(postId, commentRepository), HttpStatus.OK);
    }

    @PostMapping("addComment")
    public ResponseEntity addCommentToPost(@RequestBody PostedCommentDTO postedComment) {
        try{
            List<CommentResponseDTO> comments =this.commentService.addCommentToPost(postedComment, postRepository, commentRepository, userRepository);
            return new ResponseEntity(comments, HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
