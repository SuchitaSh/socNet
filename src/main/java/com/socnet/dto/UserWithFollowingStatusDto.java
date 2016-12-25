package com.socnet.dto;

import java.util.Date;

import com.socnet.dto.enums.UserStatus;

public class UserWithFollowingStatusDto {
	
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateOfBirth;
	private boolean following;
	private boolean follower;
	
	public UserWithFollowingStatusDto() {}
	
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
	public boolean isFollower() {
		return follower;
	}
	public void setFollower(boolean follower) {
		this.follower = follower;
	}
	public boolean isFollowing() {
		return following;
	}
	public void setFollowing(boolean following) {
		this.following = following;
	}
}
