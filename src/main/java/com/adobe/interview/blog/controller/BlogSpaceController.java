package com.adobe.interview.blog.controller;


import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.components.blogSpace.NewBlogSpaceDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.UserRepository;
import com.adobe.interview.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity getAllBlogSpaces() {

        try{
            List<BlogSpaceResponseDTO> blogSpaceResponseDTOS = this.blogService.getAllBlogSpaces(this.blogSpaceRepository);
            return new ResponseEntity<>(blogSpaceResponseDTOS, HttpStatus.OK);

        }catch (ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("addNewBlogSpace")
    public ResponseEntity addNewBlogSpace(@RequestBody NewBlogSpaceDTO newBlogSpace) {
        try{
            List<BlogSpaceResponseDTO> blogSpaceResponseDTOS = this.blogService.addNewBlogSpace(newBlogSpace, userRepository, blogSpaceRepository);
            return new ResponseEntity<>(blogSpaceResponseDTOS, HttpStatus.OK);

        }catch (ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }
}
