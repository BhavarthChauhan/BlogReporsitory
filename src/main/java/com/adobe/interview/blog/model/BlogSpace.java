package com.adobe.interview.blog.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "BlogSpace")
public class BlogSpace {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "spaceName")
    @JsonProperty(value = "spaceName")
    private String spaceName;

    @Column(name = "description")
    @JsonProperty(value = "description")
    private String description;

    @Column(name="theme")
    @JsonProperty(value = "theme")
    private String theme;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public BlogSpace() {
    }

    public BlogSpace(String spaceName, String description, String theme, User user) {
        this.spaceName = spaceName;
        this.description = description;
        this.theme = theme;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
