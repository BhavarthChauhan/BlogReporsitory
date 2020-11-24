package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.comment.PostedCommentDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RelationSupport;
import java.util.List;

public class CommentService {

    public CommentService(){

    }

    public List<Comment> getCommentsForPost(long postId, CommentRepository commentRepository){
        return commentRepository.getCommentByPostId(postId);
    }

    public List<Comment> addCommentToPost(PostedCommentDTO postedCommentDTO, PostRepository postRepository, CommentRepository commentRepository){
        List<Post> posts = postRepository.getPostById(postedCommentDTO.getPostId()) ;

        if(posts.isEmpty()){
            throw  new ResourceNotFoundException("No post found to add comment");
        }else{
            Post post = posts.get(0);
            Comment comment = new Comment(postedCommentDTO.getText(), post.getUser(), post);
            commentRepository.save(comment);

            return commentRepository.getCommentByPostId(postedCommentDTO.getPostId());
        }
    }
}
