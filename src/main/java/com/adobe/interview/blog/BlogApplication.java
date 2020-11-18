package com.adobe.interview.blog;

import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("User 1","user1","pass1"));
        this.userRepository.save(new User("User 2","user2","pass2"));
        this.userRepository.save(new User("User 3","user3","pass3"));
        this.userRepository.save(new User("User 4","user4","pass4 "));
    }
}
