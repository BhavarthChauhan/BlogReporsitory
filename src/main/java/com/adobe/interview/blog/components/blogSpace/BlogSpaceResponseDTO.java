package com.adobe.interview.blog.components.blogSpace;

public class BlogSpaceResponseDTO {

    private String idString;

    private long id;

    private String spaceName;

    private String description;

    private String theme;

    private String createdBy;


    public BlogSpaceResponseDTO(String idString, long id, String spaceName, String description, String theme, String createdBy) {
        this.idString = idString;
        this.id = id;
        this.spaceName = spaceName;
        this.description = description;
        this.theme = theme;
        this.createdBy = createdBy;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
