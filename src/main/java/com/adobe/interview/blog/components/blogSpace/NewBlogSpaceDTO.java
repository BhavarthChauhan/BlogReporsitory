package com.adobe.interview.blog.components.blogSpace;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewBlogSpaceDTO {

    @JsonProperty("spaceName")
    private String spaceName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("theme")
    private String theme;

    @JsonProperty("userName")
    private String userName;


    public NewBlogSpaceDTO(){

    }

    public NewBlogSpaceDTO(String spaceName, String description, String theme, String userName) {
        this.spaceName = spaceName;
        this.description = description;
        this.theme = theme;
        this.userName = userName;
    }


    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
