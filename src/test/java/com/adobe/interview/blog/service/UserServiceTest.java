package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.login.LoginResponseDTO;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
class UserServiceTest {


    private static final String UNKNOWN_USER_NAME = "unknownUserName";
    private static final String KNOWN_USER_NAME = "user1";
    private static final String INCORRECT_PASSWORD = "pass";
    private static final String CORRECT_PASSWORD = "pass1";
    private static final String TEST_NAME = "testName";

    @Autowired
    UserRepository userRepository;

    private UserService userService = new UserService();

    @Test
    public void signInErrorWhenUserNotPresent(){
        LoginResponseDTO loginResponseDTO = this.userService.signInUser(userRepository, UNKNOWN_USER_NAME, INCORRECT_PASSWORD);
        Assert.assertFalse(loginResponseDTO.isStatusOk());
        Assert.assertEquals("No user found", loginResponseDTO.getErrorMessage());
    }

    @Test
    public void signInErrorWhenIncorrectPassword(){
        LoginResponseDTO loginResponseDTO = this.userService.signInUser(userRepository, KNOWN_USER_NAME, INCORRECT_PASSWORD);
        Assert.assertFalse(loginResponseDTO.isStatusOk());
        Assert.assertEquals("Incorrect password", loginResponseDTO.getErrorMessage());
    }

    @Test
    public void signInSuccess(){
        LoginResponseDTO loginResponseDTO = this.userService.signInUser(userRepository, KNOWN_USER_NAME, CORRECT_PASSWORD);
        Assert.assertTrue(loginResponseDTO.isStatusOk());
        Assert.assertEquals("All ok", loginResponseDTO.getErrorMessage());
    }

    @Test
    public void signUpAlreadyExistingUser(){
        User user = new User(TEST_NAME,KNOWN_USER_NAME, CORRECT_PASSWORD);
        LoginResponseDTO loginResponseDTO = this.userService.signUpUser(user, userRepository);

        Assert.assertFalse(loginResponseDTO.isStatusOk());
        Assert.assertEquals("Username already exists", loginResponseDTO.getErrorMessage());
    }

    @Test
    public void signUpNewUser(){
        User user = new User(TEST_NAME,UNKNOWN_USER_NAME, CORRECT_PASSWORD);
        LoginResponseDTO loginResponseDTO = this.userService.signUpUser(user, userRepository);

        Assert.assertTrue(loginResponseDTO.isStatusOk());
        Assert.assertEquals("All ok", loginResponseDTO.getErrorMessage());
    }
}
