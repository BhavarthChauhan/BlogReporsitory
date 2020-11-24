package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.login.LoginResponseDTO;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;

import java.util.List;

public class UserService {


    public UserService(){

    }

    /**
     * Signins in the user by checking is userName exists and if password is correct
     * @param userRepository repo object of user table
     * @param userName userName
     * @param password password
     * @return
     */
    public LoginResponseDTO signInUser(UserRepository userRepository, String userName, String password){
        List<User> user = userRepository.getUserByUserName(userName);

        if(user.size()==0){
            return new LoginResponseDTO(false, "No user found");
        }

        if(!user.get(0).getPassword().equals(password)){
            return new LoginResponseDTO(false,"Incorrect password");
        }

        else return new LoginResponseDTO(true,"All ok");

    }

    /**
     * Creates new account of the user if userName does not exists
     * @param user user details
     * @param userRepository repo object of user table
     * @return
     */
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
