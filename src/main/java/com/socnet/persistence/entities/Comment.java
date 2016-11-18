package com.socnet.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.socnet.web.Views;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.Summary.class})
	private Long id;
	
	@Column(name = "comment_text", nullable = false)
	@JsonView({Views.Summary.class})
	private String text;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	@JsonView({Views.WithChildren.class})
	private User user;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "post_id")
	@JsonBackReference
	@JsonView({Views.WithParent.class})
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
		return "Comment [id=" + id + ", text=" + text + ", user=" + user
				+ ", post=" + post + "]";
	}

	
	
	
}
