package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.components.blogSpace.NewBlogSpaceDTO;

import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.service.BlogService;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.ArrayList;


@SpringBootTest
public class BlogSpaceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BlogService blogService = new BlogService();

    @InjectMocks
    BlogSpaceController blogSpaceController;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogSpaceController).build();
    }


    @Test
    public void allBlogSpacesFetched() throws  Exception{
        Mockito.when(
                blogService.getAllBlogSpaces(Mockito.any())
        ).thenReturn(Mockito.anyListOf(BlogSpaceResponseDTO.class));

        mockMvc.perform(get("/api/allBlogSpaces"))
                .andExpect(status().isOk());
    }

    @Test
    public void newBlogSpaceAdded() throws  Exception{

        NewBlogSpaceDTO newBlogSpaceDTO = new NewBlogSpaceDTO("testSpaceName","testDesc","testTheme","testUserName");
        Mockito.when(
                blogService.addNewBlogSpace(Mockito.any(), Mockito.any(), Mockito.any() )
        ).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/api/addNewBlogSpace")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBlogSpaceDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void newBlogSpaceAddedError() throws  Exception{

        NewBlogSpaceDTO newBlogSpaceDTO = new NewBlogSpaceDTO("testSpaceName","testDesc","testTheme","testUserName");
        Mockito.when(
                blogService.addNewBlogSpace(Mockito.any(), Mockito.any(), Mockito.any() )
        ).thenThrow(new ResourceNotFoundException("exception"));


        mockMvc.perform(post("/api/addNewBlogSpace")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBlogSpaceDTO)))
                .andExpect(status().isNotFound());

    }
}
