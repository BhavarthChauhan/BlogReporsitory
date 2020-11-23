package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.login.LoginResponseDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.service.PostService;
import com.adobe.interview.blog.service.UserService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void signInUserSuccess() throws Exception {
        Mockito.when(
                userService.signInUser(Mockito.any(), Mockito.anyString(), Mockito.anyString())
        ).thenReturn(new LoginResponseDTO());

        mockMvc.perform(get("/api/signIn/{userName}/{password}", "testUserName", "testPassword"))
                .andExpect(status().isOk());
    }

    @Test
    public void signInUserError() throws Exception {
        Mockito.when(
                userService.signInUser(Mockito.any(), Mockito.anyString(), Mockito.anyString())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(get("/api/signIn/{userName}/{password}", "testUserName", "testPassword"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void signUpUserSuccess() throws Exception {
        User user = new User("testName", "testUsername", "testPassword");
        Mockito.when(
                userService.signUpUser(Mockito.any(), Mockito.any())
        ).thenReturn(new LoginResponseDTO());

        mockMvc.perform(post("/api/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void signUpUserError() throws Exception {
        User user = new User("testName", "testUsername", "testPassword");
        Mockito.when(
                userService.signUpUser(Mockito.any(), Mockito.any())
        ).thenThrow(new ResourceNotFoundException("exception"));

        mockMvc.perform(post("/api/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNotFound());
    }
}
