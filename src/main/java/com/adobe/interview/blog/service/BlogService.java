package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.repository.BlogSpaceRepository;

import java.util.ArrayList;
import java.util.List;

public class BlogService {


    public BlogService() {


    }


    public List<BlogSpaceResponseDTO> getAllBlogSpaces(BlogSpaceRepository blogSpaceRepository) {
        List<BlogSpace> blogSpaces = blogSpaceRepository.findAll();

        List<BlogSpaceResponseDTO> blogSpaceResponseDTOS = new ArrayList<>();

        for (BlogSpace blogSpace : blogSpaces) {
            BlogSpaceResponseDTO blogSpaceResponseDTO = new BlogSpaceResponseDTO(String.valueOf(blogSpace.getId()),
                    blogSpace.getId(), blogSpace.getSpaceName(), blogSpace.getDescription(),
                    blogSpace.getTheme(), blogSpace.getUser().getUserName());
            blogSpaceResponseDTOS.add(blogSpaceResponseDTO);
        }

        return blogSpaceResponseDTOS;
    }

}
