package com.socnet.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "comment_text")
	private String text;
	
	@Column(name = "author_full_name")
	private String authorFullName;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	public Comment() {
		// TODO Auto-generated constructor stub
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

	public String getAuthorFullName() {
		return authorFullName;
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		
		this.post = post;

		if(!post.getComments().contains(this))
			post.addComment(this);
		
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", authorFullName=" + authorFullName + ", user=" + user
				+ ", post=" + post + "]";
	}

	
	
	
}
