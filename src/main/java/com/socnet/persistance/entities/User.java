package com.socnet.persistance.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "friends",
				joinColumns = @JoinColumn(name = "first_user_id"),
				inverseJoinColumns = @JoinColumn(name = "second_user_id"))
	private Set<User> followings;
	
	public User() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Set<Post> getPosts() {
		return posts;
	}


	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post){
		if(post == null){
			throw new NullPointerException("Can't add null post");
		}
		if(post.getUser() != null){
			throw new IllegalStateException("Post is already assigned to user");
		}
		
		posts.add(post);
		post.setUser(this);
		
	}
	
	public void removePost(Post post){
		
		posts.remove(post);
		post.setUser(null);
		
	}


	public Set<User> getFollowings() {
		return followings;
	}


	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}

	public void addFollowing(User user){
		if(user == null){
			throw new NullPointerException("Can't add null user");
		}
		
		followings.add(user);
		
	}
	
	public void removeFollowing(User user){
		
			followings.remove(user);
			
		}
		
	
	public Set<User> getFriends(){
		Set<User> result = new HashSet<>();
		
		for(User user : getFollowings()){
			if( user.getFollowings().contains(this)) {
				result.add(user);
			}
		}
		
		return result;
	}
		
}
