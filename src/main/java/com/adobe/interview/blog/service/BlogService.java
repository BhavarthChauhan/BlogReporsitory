package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.components.blogSpace.NewBlogSpaceDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

public class BlogService {


    public BlogService() {


    }

    /**
     * Gets all blog spaces in the db
     * @param blogSpaceRepository repo object of table BlogSpace
     * @return
     */
    public List<BlogSpaceResponseDTO> getAllBlogSpaces(BlogSpaceRepository blogSpaceRepository) {
        List<BlogSpace> blogSpaces = blogSpaceRepository.findAll();

        List<BlogSpaceResponseDTO> blogSpaceResponseDTOS = new ArrayList<>();

        for (BlogSpace blogSpace : blogSpaces) {
            BlogSpaceResponseDTO blogSpaceResponseDTO = new BlogSpaceResponseDTO(String.valueOf(blogSpace.getId()),
                    blogSpace.getId(), blogSpace.getSpaceName(), blogSpace.getDescription(),
                    blogSpace.getTheme(), blogSpace.getUser().getName());
            blogSpaceResponseDTOS.add(blogSpaceResponseDTO);
        }

        return blogSpaceResponseDTOS;
    }

    /**
     * Adds new blog space created by a user
     * @param newBlogSpaceDTO new blog space details
     * @param userRepository repo object of the user table
     * @param blogSpaceRepository repo object of the blog table
     * @return
     */
    public List<BlogSpaceResponseDTO> addNewBlogSpace(NewBlogSpaceDTO newBlogSpaceDTO, UserRepository userRepository, BlogSpaceRepository blogSpaceRepository) {
        List<User> users = userRepository.getUserByUserName(newBlogSpaceDTO.getUserName());

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        } else {
            User user = users.get(0);
            BlogSpace blogSpace = new BlogSpace(newBlogSpaceDTO.getSpaceName(), newBlogSpaceDTO.getDescription(), newBlogSpaceDTO.getTheme(), user);

            blogSpaceRepository.save(blogSpace);
            return getAllBlogSpaces(blogSpaceRepository);
        }

    }
}
