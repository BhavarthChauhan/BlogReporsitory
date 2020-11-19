package com.adobe.interview.blog;

import com.adobe.interview.blog.model.Comment;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.CommentRepository;
import com.adobe.interview.blog.repository.PostRepository;
import com.adobe.interview.blog.repository.UserRepository;
import org.hibernate.annotations.NaturalId;
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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("User 1","user1","pass1");
        User user2 = new User("User 2","user2","pass2");
        User user3 = new User("User 3","user3","pass3");
        User user4 = new User("User 4","user4","pass4");
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.save(user4);

        Post post1 = new Post("Post Title 1","Post Description 1","Post Content 1",user1);
        Post post2 = new Post("Post Title 2","Post Description 2","Post Content 2",user1);
        Post post3 = new Post("Post Title 3","Post Description 3","Post Content 3",user1);

        Post post4 = new Post("Post Title 6","Post Description 6","Post Content 6",user2);
        Post post5 = new Post("Post Title 4","Post Description 4","Post Content 4",user2);
        Post post6 = new Post("Post Title 5","Post Description 5","Post Content 5",user2);

        this.postRepository.save(post1);
        this.postRepository.save(post2);
        this.postRepository.save(post3);
        this.postRepository.save(post4);
        this.postRepository.save(post5);
        this.postRepository.save(post6);


        Comment comment1 = new Comment("Comment 1", user1, post1);
        Comment comment2 = new Comment("Comment 2", user2, post1);

        Comment comment3 = new Comment("Comment 1", user1, post2);
        Comment comment4 = new Comment("Comment 2", user2, post2);

        this.commentRepository.save(comment1);
        this.commentRepository.save(comment2);
        this.commentRepository.save(comment3);
        this.commentRepository.save(comment4);
    }
}
