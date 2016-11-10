package com.socnet.persistance.entities;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "posting_date")
	private Date postingDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments;
	
	public Post() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		user.getPosts().add(this);
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment){
		if(comment == null){
			throw new NullPointerException("Can't add null comment");
		}
		
		if(comment.getPost() != null){
			throw new IllegalStateException("The comment's post is already assigned");
		}
		
		comments.add(comment);
		comment.setPost(this);
		
	}
	
	public void removeComment(Comment comment){
		
		comments.remove(comment);
		comment.setPost(null);
	}
	
	
	
}
