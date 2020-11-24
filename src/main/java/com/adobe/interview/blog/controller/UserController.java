package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.login.LoginResponseDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {

    UserService userService = new UserService();

    @Autowired
    private UserRepository userRepository;



    @GetMapping(value = "signIn/{userName}/{password}")
    public ResponseEntity signInUser(@PathVariable("userName") String userName, @PathVariable("password")String password ){
        try {
            LoginResponseDTO loginResponseDTO = this.userService.signInUser(userRepository, userName, password);
            return new ResponseEntity(loginResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    @PostMapping(value="/signUp"  ,headers="Accept=application/json")
    public ResponseEntity signUpUser(@RequestBody User user){
        try {
            LoginResponseDTO loginResponseDTO = this.userService.signUpUser(user, userRepository);
            return new ResponseEntity(loginResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
