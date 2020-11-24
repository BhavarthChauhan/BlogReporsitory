package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.comment.PostedCommentDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CommentControllerTests {

    private MockMvc mockMvc;


    @Mock
    CommentService commentService;

    @InjectMocks
    CommentController commentController;


    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }


    @Test
    public void getCommentForPost() throws Exception{
        Mockito.when(
                commentService.getCommentsForPost(Mockito.anyLong(), Mockito.any())
        ).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/commentsForPost/{postId}",1))
                .andExpect(status().isOk());
    }

    @Test
    public void addCommentToPostSuccess() throws  Exception{
        PostedCommentDTO postedCommentDTO = new PostedCommentDTO("testUser",1,"testText");

        Mockito.when(
                commentService.addCommentToPost(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())
        ).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/api/addComment")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(postedCommentDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void addCommentToPostError() throws  Exception{
        PostedCommentDTO postedCommentDTO = new PostedCommentDTO("testUser",1,"testText");

        Mockito.when(
                commentService.addCommentToPost(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(post("/api/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postedCommentDTO)))
                .andExpect(status().isNotFound());
    }
}
