package com.adobe.interview.blog.components.login;

import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;

import java.util.List;

public class Login {

    public LoginResponseDTO signUserIn(String userName, String password, UserRepository userRepository){

        List<User> user = userRepository.getUserByUserName(userName);

        if(user.size()==0){
            return new LoginResponseDTO(false, "No user found");
        }

        if(!user.get(0).getPassword().equals(password)){
            return new LoginResponseDTO(false,"Incorrect password");
        }

        else return new LoginResponseDTO(true,"All ok");
    }

    public LoginResponseDTO signUpUser(User user, UserRepository userRepository){
        List<User> users = userRepository.getUserByUserName(user.getUserName());
        if(users.isEmpty()){
            userRepository.save(new User(user.getName(), user.getUserName(), user.getPassword()));
            return new LoginResponseDTO(true , "All ok");
        }else{
            return new LoginResponseDTO(false, "Username already exists");
        }
    }

}
