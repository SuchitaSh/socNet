package com.socnet.dto;

public class BasicCommentDto {
    private Long id;
    private String text;
    private BasicUserDto user;
    private Long postId;

    public BasicCommentDto() {
    }

    public BasicCommentDto(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BasicUserDto getUser() {
        return user;
    }

    public void setUser(BasicUserDto user) {
        this.user = user;
    }
}
