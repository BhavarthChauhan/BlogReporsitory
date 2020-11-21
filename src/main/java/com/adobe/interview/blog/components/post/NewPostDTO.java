package com.adobe.interview.blog.components.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPostDTO {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    private String content;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("blogSpaceId")
    private long blogSpaceId;

    public NewPostDTO( long id, String title, String description, String content, String userName, long blogSpaceId) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.userName = userName;
        this.blogSpaceId = blogSpaceId;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getBlogSpaceId() {
        return blogSpaceId;
    }

    public void setBlogSpaceId(long blogSpaceId) {
        this.blogSpaceId = blogSpaceId;
    }
}
