package com.adobe.interview.blog.controller;

import com.adobe.interview.blog.components.login.Login;
import com.adobe.interview.blog.components.login.LoginResponse;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {

    Login login = new Login();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){
            return this.userRepository.findAll();
    }


    @GetMapping(value = "signIn/{userName}/{password}")
    public ResponseEntity<LoginResponse> signInUser(@PathVariable("userName") String userName, @PathVariable("password")String password ){

        return new ResponseEntity<LoginResponse>(login.signUserIn(userName, password, userRepository), HttpStatus.OK);
    }

    @PostMapping(value="/signUp"  ,headers="Accept=application/json")
    public ResponseEntity<LoginResponse> signUpUser(@RequestBody User user){
        System.out.println("asd");

        return new ResponseEntity<LoginResponse>(login.signUpUser(user, userRepository), HttpStatus.OK);

    }
}
