package com.socnet.dto;

import java.util.Date;

public class BasicPostDto {
    private Long id;
    private String title;
    private String text;
    private Date postingDate;
    private BasicUserDto user;
    private BasicUserDto author;

    public BasicPostDto() {
    }

    public BasicPostDto(Long id) {
        this.id = id;
    }

    public BasicUserDto getAuthor() {
        return author;
    }

    public void setAuthor(BasicUserDto author) {
        this.author = author;
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

    public BasicUserDto getUser() {
        return user;
    }

    public void setUser(BasicUserDto user) {
        this.user = user;
    }
}
