package com.socnet.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false)
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

	@ManyToMany(mappedBy = "followings")
	private Set<User> followers = new HashSet<>();
	
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Notification> notifications = new HashSet<>();
	
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

	@JsonIgnore
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

	@JsonIgnore
	public Set<User> getFollowings() {
		return followings;
	}


	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}
	
	public void addFollowing(User user){
		if(user == null){
			throw new NullPointerException("Can't add null user as following");
		}
		
		followings.add(user);
		if(! user.getFollowers().contains(this)){
			user.addFollower(this);
		}
		
	}
	public void removeFollowing(User user){
		
			followings.remove(user);
			
		}
	
	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	public void addFollower(User follower){
		if(follower == null){
			throw new NullPointerException("Cant't add null user as follower");
		}
		
		this.followers.add(follower);
		if(! follower.followings.contains(this)){
			follower.addFollowing(this);
		}
	}
	
	public void removeFollower(User follower){
		this.followers.remove(follower);
	} 


	@JsonIgnore
	public Set<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification){
		
		if(notification == null){
			throw new NullPointerException("Can't pass null notification");
		}
		
		notifications.add(notification);
		
		if(notification.getReceiver() == null){
			notification.setReceiver(this);
		}
	}
	
	public void removeNotification(Notification notification){
		
		notifications.remove(notification);
		notification.setReceiver(null);
		
	}


	@JsonIgnore
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
		return "User [id=" + id + ", userName=" + username + "]";
	}

//	@Override   (stackOverflow! RL)
//	public String toString() {
//		return "User [id=" + id + ", userName=" + username + ", password=" + password + ", firstName=" + firstName
//				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", posts=" + posts + ", followings="
//				+ followings + "]";
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return id.equals(user.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
