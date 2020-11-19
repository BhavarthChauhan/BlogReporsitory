package com.adobe.interview.blog.components.Login;

import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;

import java.util.List;

public class Login {

    public LoginResponse signUserIn(String userName, String password, UserRepository userRepository){

        List<User> user = userRepository.getUserByUserName(userName);

        if(user.size()==0){
            return new LoginResponse(false, "No user found");
        }

        if(!user.get(0).getPassword().equals(password)){
            return new LoginResponse(false,"Incorrect password");
        }

        else return new LoginResponse(true,"All ok");
    }

    public LoginResponse signUpUser(User user, UserRepository userRepository){
        List<User> users = userRepository.getUserByUserName(user.getUserName());
        if(users.isEmpty()){
            userRepository.save(new User(user.getName(), user.getUserName(), user.getPassword()));
            return new LoginResponse(true , "All ok");
        }else{
            return new LoginResponse(false, "Username already exists");
        }
    }

}
