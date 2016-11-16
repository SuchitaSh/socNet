package com.socnet.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.socnet.web.Views;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.Summary.class})
	private Long id;
	
	@Column(name = "title")
	@JsonView({Views.Summary.class})
	private String title;
	
	@Column(name = "text")
	@JsonView({Views.Summary.class})
	private String text;

	@Temporal(TemporalType.DATE)
	@Column(name = "posting_date")
	@JsonView({Views.Summary.class})
	private Date postingDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonView({Views.WithParent.class})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	@JsonView({Views.Summary.class})
	private User author;
	
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@JsonView({Views.WithChildren.class})
	private Set<Comment> comments = new HashSet<>();
	
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
		this.user = user;
		
		if(! user.getPosts().contains(this)){
			user.addPost(this);
		}
		
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
		
		comments.add(comment);
		
		if(comment.getPost() == null)
			comment.setPost(this);
		
	}
	
	public void removeComment(Comment comment){
		
		comments.remove(comment);
		comment.setPost(null);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", postingDate=" + postingDate + ", user="
				+ user + ", comments=" + comments + "]";
	}
	
	
	
}
