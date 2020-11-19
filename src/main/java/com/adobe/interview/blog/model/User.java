package com.adobe.interview.blog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name ="userName")
    @JsonProperty(value="userName")
    private String userName;

     @Column(name="password")
     @JsonProperty(value="password")
    private String password;

    public User(){

    }

    public User( String firstName, String userName, String password) {
        this.name = firstName;
        this.userName = userName;
        this.password = password;
    }

    public User( long id, String firstName, String userName, String password) {
        this.id = id;
        this.name = firstName;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String lastName) {
        this.userName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
