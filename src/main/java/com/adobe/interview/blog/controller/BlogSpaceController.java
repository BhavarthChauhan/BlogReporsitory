package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.components.blogSpace.NewBlogSpaceDTO;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class BlogSpaceController {

    @Autowired
    BlogSpaceRepository blogSpaceRepository;

    @Autowired
    UserRepository userRepository;

    BlogService blogService = new BlogService();

    @GetMapping("allBlogSpaces")
    public List<BlogSpaceResponseDTO> getAllBlogSpaces() {
        return this.blogService.getAllBlogSpaces(this.blogSpaceRepository);
    }

    @PostMapping("addNewBlogSpace")
    public List<BlogSpaceResponseDTO> addNewBlogSpace(@RequestBody NewBlogSpaceDTO newBlogSpace){

        User user = this.userRepository.getUserByUserName(newBlogSpace.getUserName()).get(0);
        BlogSpace blogSpace = new BlogSpace(newBlogSpace.getSpaceName(), newBlogSpace.getDescription(), newBlogSpace.getTheme(), user);

        this.blogSpaceRepository.save(blogSpace);
        return this.blogService.getAllBlogSpaces(this.blogSpaceRepository);


    }
}
