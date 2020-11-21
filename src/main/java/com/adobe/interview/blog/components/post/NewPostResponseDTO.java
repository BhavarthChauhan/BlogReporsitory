package com.adobe.interview.blog.components.post;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class NewPostResponseDTO {

    private List<NewPostDTO> newPosts;
    private String userName;
    private String spaceTheme;
    private long blogSpaceId;

    public NewPostResponseDTO() {
        this.newPosts = new ArrayList<>();
    }

    public NewPostResponseDTO(List<NewPostDTO> newPosts, String userName, String spaceTheme, long blogSpaceId) {
        this.newPosts = newPosts;
        this.userName = userName;
        this.spaceTheme = spaceTheme;
        this.blogSpaceId = blogSpaceId;
    }

    public List<NewPostDTO> getNewPosts() {
        return newPosts;
    }

    public void setNewPosts(List<NewPostDTO> newPosts) {
        this.newPosts = newPosts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSpaceTheme() {
        return spaceTheme;
    }

    public void setSpaceTheme(String spaceTheme) {
        this.spaceTheme = spaceTheme;
    }

    public long getBlogSpaceId() {
        return blogSpaceId;
    }

    public void setBlogSpaceId(long blogSpaceId) {
        this.blogSpaceId = blogSpaceId;
    }
}
