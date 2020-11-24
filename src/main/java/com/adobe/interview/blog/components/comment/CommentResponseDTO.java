package com.adobe.interview.blog.components.comment;

import com.adobe.interview.blog.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentResponseDTO {

    @JsonProperty("commentBy")
    private String commentBy;

    @JsonProperty("text")
    private String text;

    public CommentResponseDTO(){

    }

    public CommentResponseDTO(String commentBy, String text) {
        this.commentBy = commentBy;
        this.text = text;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
