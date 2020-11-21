package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;

import java.util.List;

public class PostService {

    public PostService() {
    }

    public NewPostResponseDTO getPosts(List<Post> posts, User user, BlogSpace blogSpace){

        NewPostResponseDTO newPostResponseDTO = new NewPostResponseDTO();

        for(Post post : posts){
            NewPostDTO newPostDTO = new NewPostDTO(post.getId(),post.getTitle(), post.getDescription(), post.getContent(), post.getUser().getUserName(), post.getBlogSpace().getId());
            newPostResponseDTO.getNewPosts().add(newPostDTO);
        }

        newPostResponseDTO.setBlogSpaceId(blogSpace.getId());
        newPostResponseDTO.setSpaceTheme(blogSpace.getTheme());
        newPostResponseDTO.setUserName(user.getUserName());

        return  newPostResponseDTO;

    }
}
