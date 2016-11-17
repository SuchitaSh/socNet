package com.socnet.dto;

import java.util.Date;
import java.util.Set;

public class PostWithCommentsDto {
    private Long id;
    private String title;
    private String text;
    private Date postingDate;
    private BasicUserDto userDto;
    private BasicUserDto author;
    private Set<BasicCommentDto> comments;

    public PostWithCommentsDto() {
    }

    public PostWithCommentsDto(Long id) {
        this.id = id;
    }

    public BasicUserDto getAuthor() {
        return author;
    }

    public void setAuthor(BasicUserDto author) {
        this.author = author;
    }

    public Set<BasicCommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<BasicCommentDto> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BasicUserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(BasicUserDto userDto) {
        this.userDto = userDto;
    }
}
