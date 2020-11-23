package com.adobe.interview.blog.components.comment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostedCommentDTO {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("postId")
    private long postId;

    @JsonProperty("text")
    private String text;

    public PostedCommentDTO(){

    }

    public PostedCommentDTO(String userName, long postId, String text) {
        this.userName = userName;
        this.postId = postId;
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
