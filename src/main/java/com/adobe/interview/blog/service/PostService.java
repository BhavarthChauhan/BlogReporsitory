package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.model.User;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    public NewPostResponseDTO getAllPostsBySpace(long blogSpaceId, BlogSpaceRepository blogSpaceRepository, PostRepository postRepository){

        Optional<BlogSpace> blogSpaceOptional = blogSpaceRepository.findById(blogSpaceId);
        if(blogSpaceOptional.isPresent()){
            BlogSpace blogSpace = blogSpaceOptional.get();
            return this.getPosts(postRepository.getPostByBlogSpace(blogSpaceId), blogSpace.getUser(), blogSpace);
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"No blog space found");
        }
    }

    public List<Post> getPostsByUser(long userId, PostRepository postRepository){
        List<Post> posts = postRepository.getPostsByUserId(userId);
        if(posts.isEmpty()){
            throw  new ResourceNotFoundException("No user found");
        }else{
            return posts;
        }
    }

    public Post getPostDetails(PostRepository postRepository, long postId){
        List<Post> posts = postRepository.getPostById(postId);
        if(posts.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not found");
        }else{
            return posts.get(0);
        }
    }

    public NewPostResponseDTO addNewPost(NewPostDTO newPostDTO, BlogSpaceRepository  blogSpaceRepository, PostRepository postRepository){

        Optional<BlogSpace> blogSpaceOptional = blogSpaceRepository.findById(newPostDTO.getBlogSpaceId());
        if(blogSpaceOptional.isPresent()){
            BlogSpace blogSpace1 = blogSpaceOptional.get();
            Post post = new Post(newPostDTO.getTitle(), newPostDTO.getDescription(), newPostDTO.getContent(), blogSpace1.getUser(),blogSpace1);
            postRepository.save(post);
            return this.getPosts(postRepository.getPostByBlogSpace(blogSpace1.getId()), blogSpace1.getUser(), blogSpace1);
        }else{
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "NO blog space found to save post");
        }


    }
}
