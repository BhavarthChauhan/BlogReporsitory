package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.comment.CommentResponseDTO;
import com.adobe.interview.blog.components.comment.PostedCommentDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RelationSupport;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

    public CommentService() {

    }

    /**
     * Gets all the comment for the provided post id
     * @param postId postId of the post
     * @param commentRepository repo object of comment table
     * @return
     */
    public List<CommentResponseDTO> getCommentsForPost(long postId, CommentRepository commentRepository) {
        List<Comment> comments = commentRepository.getCommentByPostId(postId);
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        for(Comment comment : comments){
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment.getUser().getName(), comment.getText());
            commentResponseDTOS.add(commentResponseDTO);
        }
        return commentResponseDTOS;
    }

    /**
     * Adds a new comment to the post
     * @param postedCommentDTO comment with details
     * @param postRepository repo object of post table
     * @param commentRepository repo object of comment table
     * @param userRepository repo object of user table
     * @return
     */
    public List<CommentResponseDTO> addCommentToPost(PostedCommentDTO postedCommentDTO, PostRepository postRepository,
                                          CommentRepository commentRepository, UserRepository userRepository) {
        List<Post> posts = postRepository.getPostById(postedCommentDTO.getPostId());

        if (posts.isEmpty()) {
            throw new ResourceNotFoundException("No post found to add comment");
        } else {
            List<User> users = userRepository.getUserByUserName(postedCommentDTO.getUserName());
            if (users.isEmpty()) {
                throw new ResourceNotFoundException("No user found to add comment");
            }
            User user = users.get(0);
            Post post = posts.get(0);
            Comment comment = new Comment(postedCommentDTO.getText(), user, post);
            commentRepository.save(comment);

            return this.getCommentsForPost(postedCommentDTO.getPostId(), commentRepository);
        }
    }
}
