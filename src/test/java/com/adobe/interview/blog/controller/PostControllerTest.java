package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    PostService postService;

    @InjectMocks
    PostController postController;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void getAllPostsForSpaceSuccess() throws Exception {
        Mockito.when(
                postService.getAllPostsBySpace(Mockito.anyLong(), Mockito.any(), Mockito.any())
        ).thenReturn(new NewPostResponseDTO());

        mockMvc.perform(get("/api/allPostsByBlogSpace/{blogSpaceId}", 1))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllPostsForSpaceError() throws Exception {
        Mockito.when(
                postService.getAllPostsBySpace(Mockito.anyLong(), Mockito.any(), Mockito.any())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(get("/api/allPostsByBlogSpace/{blogSpaceId}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPostDetailsSuccess() throws Exception {
        Mockito.when(
                postService.getPostDetails(Mockito.any(), Mockito.anyLong())
        ).thenReturn(new Post());

        mockMvc.perform(get("/api/postDetails/{postId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostDetailsError() throws Exception {
        Mockito.when(
                postService.getPostDetails(Mockito.any(), Mockito.anyLong())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(get("/api/postDetails/{postId}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPostByUserSuccess() throws Exception {
        Mockito.when(
                postService.getPostsByUser(Mockito.anyLong(), Mockito.any())
        ).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/postsByUser/{userId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void getPostByUserError() throws Exception {
        Mockito.when(
                postService.getPostsByUser(Mockito.anyLong(), Mockito.any())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(get("/api/postsByUser/{userId}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPostSuccess() throws Exception {
        NewPostDTO newPostDTO = new NewPostDTO(1, "testTitle", "testDesc", "testContent", "testUser", 1);

        Mockito.when(
                postService.addNewPost(Mockito.any(), Mockito.any(), Mockito.any())
        ).thenReturn(new NewPostResponseDTO());

        mockMvc.perform(post("/api/addPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPostDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void addPostError() throws Exception {
        NewPostDTO newPostDTO = new NewPostDTO(1, "testTitle", "testDesc", "testContent", "testUser", 1);

        Mockito.when(
                postService.addNewPost(Mockito.any(), Mockito.any(), Mockito.any())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(post("/api/addPost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPostDTO)))
                .andExpect(status().isNotFound());
    }
}
