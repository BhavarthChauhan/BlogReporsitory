package com.adobe.interview.blog.service;

import com.adobe.interview.blog.components.blogSpace.BlogSpaceResponseDTO;
import com.adobe.interview.blog.components.blogSpace.NewBlogSpaceDTO;
import com.adobe.interview.blog.exception.ResourceNotFoundException;
import com.adobe.interview.blog.repository.BlogSpaceRepository;
import com.adobe.interview.blog.repository.UserRepository;
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
public class BlogServiceTests {

    @Autowired
    private BlogSpaceRepository blogSpaceRepository;

    @Autowired
    private UserRepository userRepository;

    BlogService blogService = new BlogService();

    private static final String USER_NAME_IN_DB = "user1";
    private static final String USER_NAME_NOT_IN_DB = "unknownUser";

    @Test
    public void allBlogSpacesReturned() {
        List<BlogSpaceResponseDTO> blogSpaceResponseDTO = this.blogService.getAllBlogSpaces(blogSpaceRepository);
        Assert.assertNotNull(blogSpaceResponseDTO);
        Assert.assertTrue(blogSpaceResponseDTO.size() > 0);
    }

    @Test
    public void newBlogSpaceAddedWhenUserPresent() {

        List<BlogSpaceResponseDTO> beforeSaveSpaces = this.blogService.getAllBlogSpaces(blogSpaceRepository);
        NewBlogSpaceDTO newBlogSpaceDTO = new NewBlogSpaceDTO("testSpace", "testDescription", "dark", USER_NAME_IN_DB);

        List<BlogSpaceResponseDTO> afterSaveSpaces = this.blogService.addNewBlogSpace(newBlogSpaceDTO, userRepository, blogSpaceRepository);
        Assert.assertEquals(beforeSaveSpaces.size() + 1, afterSaveSpaces.size());

        BlogSpaceResponseDTO blogSpaceResponseDTO = null;
        for (BlogSpaceResponseDTO blogSpaceResponseDTO1 : afterSaveSpaces) {
            if (blogSpaceResponseDTO1.getSpaceName().equals("testSpace")) {
                blogSpaceResponseDTO = blogSpaceResponseDTO1;
            }
        }
        Assert.assertNotNull(blogSpaceResponseDTO);

    }


    @Test
    public void newBlogSpaceAddedWhenUserNotPresent() {
        NewBlogSpaceDTO newBlogSpaceDTO = new NewBlogSpaceDTO("testSpace", "testDescription", "dark", USER_NAME_NOT_IN_DB);
        try {
            List<BlogSpaceResponseDTO> afterSaveSpaces = this.blogService.addNewBlogSpace(newBlogSpaceDTO, userRepository, blogSpaceRepository);
            fail("No exception thrown");
        } catch (ResourceNotFoundException e) {
            Assert.assertEquals("User not found", e.getMessage());
        }

    }
}
