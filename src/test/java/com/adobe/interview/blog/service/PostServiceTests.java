package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.post.NewPostDTO;
import com.adobe.interview.blog.components.post.NewPostResponseDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.model.BlogSpace;
import com.adobe.interview.blog.model.Post;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.PostRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static junit.framework.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostServiceTests {

    private static final long BLOG_SPACE_ID_IN_DB = 1;
    private static final long BLOG_SPACE_ID_NOT_IN_DB = 10;

    private static final long POST_ID_IN_DB=1;
    private static final long POST_ID_NOT_IN_DB=10;

    private static final long USER_ID_IN_DB = 1;
    private static final long USER_ID_NOT_IN_DB = 10;

    @Autowired
    BlogSpaceRepository blogSpaceRepository;

    @Autowired
    PostRepository postRepository;

    private PostService postService = new PostService();

    @Test
    public void getPostDTO(){
        List<Post> posts = this.postRepository.getPostById(BLOG_SPACE_ID_IN_DB);
        List<BlogSpace> blogSpace = this.blogSpaceRepository.getBlogSpaceById(1);

        NewPostResponseDTO newPostResponseDTO = this.postService.getPosts(posts,blogSpace.get(0).getUser(), blogSpace.get(0) );
        Assert.assertEquals(newPostResponseDTO.getNewPosts().size(), posts.size());
        Assert.assertEquals(newPostResponseDTO.getBlogSpaceId(), blogSpace.get(0).getId());
        Assert.assertEquals(newPostResponseDTO.getSpaceTheme(), blogSpace.get(0).getTheme());
        Assert.assertEquals(newPostResponseDTO.getUserName(), blogSpace.get(0).getUser().getUserName());

    }

    @Test
    public void getPostsForKnownBlogSpace(){
        NewPostResponseDTO newPostResponseDTO = this.postService.getAllPostsBySpace(BLOG_SPACE_ID_IN_DB,  blogSpaceRepository, postRepository);
        Assert.assertNotNull(newPostResponseDTO);
        Assert.assertTrue(newPostResponseDTO.getNewPosts().size()>0);
    }

    @Test
    public void getPostsForUnknownBlogSpace(){
        try{
            NewPostResponseDTO newPostResponseDTO = this.postService.getAllPostsBySpace(BLOG_SPACE_ID_NOT_IN_DB,  blogSpaceRepository, postRepository);
            fail("No exception thrown");
        }catch (ResourceNotFoundException e) {
            Assert.assertEquals("No blog space found", e.getMessage());
        }
    }

    @Test
    public void getPostDetailsForKnownPost(){
        Post post = this.postService.getPostDetails(postRepository, POST_ID_IN_DB);
        Assert.assertNotNull(post);
        Assert.assertEquals(POST_ID_IN_DB, post.getId());
    }

    @Test
    public void getPostDetailsForUnknownPost(){
        try{
            Post post = this.postService.getPostDetails(postRepository, POST_ID_NOT_IN_DB);
            fail("No exception thrown");
        }catch(ResourceNotFoundException e) {
            Assert.assertEquals("Post not found", e.getMessage());
        }
    }

    @Test
    public void newPostAddedToBlogSpace(){
        NewPostResponseDTO postsBefore = this.postService.getAllPostsBySpace(BLOG_SPACE_ID_IN_DB, blogSpaceRepository, postRepository);
        NewPostDTO newPostDTO = new NewPostDTO(6, "testTitle","testDescription","testContent","user1",BLOG_SPACE_ID_IN_DB);
        this.postService.addNewPost(newPostDTO, blogSpaceRepository, postRepository);

        NewPostResponseDTO postsAfter = this.postService.getAllPostsBySpace(BLOG_SPACE_ID_IN_DB, blogSpaceRepository, postRepository);
        Assert.assertEquals(postsBefore.getNewPosts().size()+1, postsAfter.getNewPosts().size());

        NewPostDTO newPostDTO1 = null;
        for(NewPostDTO newPostDTO2: postsAfter.getNewPosts()){
            if(newPostDTO2.getTitle().equals("testTitle")){
                newPostDTO1 = newPostDTO2;
            }
        }
        Assert.assertNotNull(newPostDTO1);
    }

    @Test
    public void newPostAddedToUnknownBlogSpace(){
        try{
            NewPostDTO newPostDTO = new NewPostDTO(6, "testTitle","testDescription","testContent","user1",BLOG_SPACE_ID_NOT_IN_DB);
            this.postService.addNewPost(newPostDTO, blogSpaceRepository, postRepository);
            fail("No exception thrown");
        }catch (ResourceNotFoundException e){
            Assert.assertEquals("NO blog space found to save post", e.getMessage());
        }
    }

    @Test
    public void getPostsByKnownUser(){
        List<Post> posts = this.postService.getPostsByUser(USER_ID_IN_DB, postRepository);
        Assert.assertTrue(posts.size()>0);
    }

    @Test
    public void getPostsByUnKnownUser(){
        try {
            List<Post> posts = this.postService.getPostsByUser(USER_ID_NOT_IN_DB, postRepository);
            fail("NO exception thrown");
        }catch (ResourceNotFoundException e){
            Assert.assertEquals("No user found", e.getMessage());
        }

    }
}
