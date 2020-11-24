package com.adobe.interview.blog.service;


import com.adobe.interview.blog.components.comment.PostedCommentDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static junit.framework.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentServiceTests {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    CommentService commentService  = new CommentService();

    private static final long POST_ID_IN_DB   = 1;
    private static final long POST_ID_NOT_IN_DB   = 10;


    @Test
    public void commentsForPostFetched(){
        List<Comment> comments = this.commentService.getCommentsForPost(POST_ID_IN_DB,commentRepository);
        Assert.assertNotNull(comments);
        Assert.assertTrue(comments.size()>0);
    }

    @Test
    public void commentsForNonAvailablePostFetched(){
        List<Comment> comments = this.commentService.getCommentsForPost(POST_ID_NOT_IN_DB,commentRepository);
        Assert.assertNotNull(comments);
        Assert.assertEquals(0, comments.size());
    }

    @Test
    public void newCommentAddedToPost(){
        List<Comment> commentsBefore = this.commentService.getCommentsForPost(POST_ID_IN_DB,commentRepository);
        PostedCommentDTO postedCommentDTO = new PostedCommentDTO("user1", POST_ID_IN_DB,"testComment");

        this.commentService.addCommentToPost(postedCommentDTO, postRepository, commentRepository);
        List<Comment> commentsAfter = this.commentService.getCommentsForPost(POST_ID_IN_DB,commentRepository);


        Assert.assertEquals(commentsBefore.size()+1, commentsAfter.size());

        Comment addedComment = null;
        for(Comment comment: commentsAfter){
            if(comment.getText().equals("testComment"))
                addedComment= comment;
        }
        Assert.assertNotNull(addedComment);
    }

    @Test
    public void commentAddedForPostNotInDB(){
        try{
            PostedCommentDTO postedCommentDTO = new PostedCommentDTO("user1", POST_ID_NOT_IN_DB,"testComment");
            this.commentService.addCommentToPost(postedCommentDTO, postRepository, commentRepository);
            fail("No exception thrown");
        }catch (ResourceNotFoundException e){
            Assert.assertEquals("No post found to add comment", e.getMessage());
        }
    }


}
