package com.socnet.persistance.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "friends",
				joinColumns = @JoinColumn(name = "first_user_id"),
				inverseJoinColumns = @JoinColumn(name = "second_user_id"))
	private Set<User> followings = new HashSet<>();
	
	public User() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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
		posts.add(post);
		if(post.getUser() == null)
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


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", posts=" + posts + ", followings="
				+ followings + "]";
	}
	
	
}
