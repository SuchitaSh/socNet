package com.socnet.service;

import com.socnet.dto.BasicPostDto;
import com.socnet.persistence.entities.Notification;
import com.socnet.persistence.entities.Post;
import com.socnet.persistence.entities.User;
import com.socnet.persistence.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService {
	private UsernameStorage principal;
	private UsersRepository usersRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserService(UsernameStorage principal, UsersRepository usersRepository, ModelMapper modelMapper) {
		this.principal = principal;
		this.usersRepository = usersRepository;
		this.modelMapper = modelMapper;
	}

	public User getCurrentUser() {
		if (principal.getUsername() == null) {
			return null;
		}
		return usersRepository.findByUsername(principal.getUsername());
	}

	@Transactional
	public BasicPostDto addPost(String text, String title) { //now return Post

		Post post = new Post();
		post.setText(text);
		post.setPostingDate(new Date());
		post.setTitle(title);
		User user = usersRepository.findByUsername(principal.getUsername());
		post.setUser(user);
		user.addPost(post);
		usersRepository.save(user);

		return modelMapper.map(user, BasicPostDto.class);
	}

	public boolean isUsernameAvailable(String username) {

		return usersRepository.findByUsername(username) == null;
	}

	public void save(User user) {
		usersRepository.save(user);
	}


	//get users with with just username, firstName, lastName field
	//it is possibly stuped solution for self reference json formatting problem
	@Transactional
	public Set<User> getCurrentUserFriends() {
		User user = usersRepository.findByUsername(principal.getUsername());
		Set<User> friends = new HashSet<>();
		User newUser;
		for (User u : user.getFriends()) {
			newUser = new User();
			newUser.setId(u.getId());
			newUser.setUsername(u.getUsername());
			newUser.setFirstName(u.getFirstName());
			newUser.setLastName(u.getLastName());
			friends.add(newUser);
		}

		return friends;
	}

	@Transactional
	public Set<User> getUserFriends(String username) {

		User user = usersRepository.findByUsername(username);
		Set<User> friends = new HashSet<>();
		User newUser;
		for (User u : user.getFriends()) {
			newUser = new User();
			newUser.setId(u.getId());
			newUser.setUsername(u.getUsername());
			newUser.setFirstName(u.getFirstName());
			newUser.setLastName(u.getLastName());
			friends.add(newUser);
		}
		return friends;
	}

	@Transactional
	public Set<User> getAllUsersInfo() {

		Set<User> allUsers = new HashSet<>();
		User newUser;
		for (User u : usersRepository.findAll()) {
			newUser = new User();
			newUser.setId(u.getId());
			newUser.setUsername(u.getUsername());
			newUser.setFirstName(u.getFirstName());
			newUser.setLastName(u.getLastName());
			allUsers.add(newUser);
		}

		return allUsers;
	}

	public User findUserByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	@Transactional
	public Notification addNotificationToUser(String username, String eventType) {
		User receiver = findUserByUsername(username);
		User author = usersRepository.findByUsername(principal.getUsername());

		Notification notification = new Notification();
		notification.setAuthor(author);
		receiver.addNotification(notification);
		notification.setEventType(eventType);

		usersRepository.save(receiver);
		return notification;
	}

	@Transactional
	public void removeNotificationFromUser(String username, Long notificationId) {
		User user = usersRepository.findByUsername(username);
		user.getNotifications().removeIf(notification -> notification.getId().equals(notificationId));
		usersRepository.save(user);
	}

	@Transactional
	public void addCurrentUserFollowing(String username) {
		User currentUser = getCurrentUser();
		User following = usersRepository.findByUsername(username);
		currentUser.addFollowing(following);
		usersRepository.save(currentUser);

	}

	@Transactional
	public Set<Notification> getUserNotifications(String username) {
		User user = usersRepository.findByUsername(username);

		Set<Notification> result = new TreeSet<>(new Comparator<Notification>() {
			@Override
			public int compare(Notification o1, Notification o2) {
				return (int) (o1.getId() - o2.getId());
			}
		});

		for (Notification n : user.getNotifications()) {
			result.add(n);
		}

		return result;
	}

	@Transactional
	public Set<Notification> getCurrentUserNotifications() {
		User user = getCurrentUser();

		Set<Notification> result = new TreeSet<>(new Comparator<Notification>() {
			@Override
			public int compare(Notification o1, Notification o2) {
				return (int) (o1.getId() - o2.getId());
			}
		});

		for (Notification n : user.getNotifications()) {
			result.add(n);
		}

		return result;
	}

	@Transactional
	public boolean isCurrentUserFriendOf(User maybeFriend) {
		User currentUser = getCurrentUser();
		return currentUser.getFriends().contains(maybeFriend);
	}

	@Transactional
	public boolean isCurrentUserFollowerOf(User maybeFollower) {
		User currentUser = getCurrentUser();
		return getFollowersOfUser(currentUser.getUsername()).contains(maybeFollower);
	}

	@Transactional
	public Set<User> getFollowersOfUser(String username) {
		User user = usersRepository.findByUsername(username);
		Set<User> follower = new HashSet<>();
		User newUser = new User();
		Set<Notification> notifications = user.getNotifications();
		for (Notification n: notifications) {
			newUser = n.getAuthor();
			follower.add(newUser);
		}
		Set<User> friends = getUserFriends(username);
		follower.removeAll(friends);
		return follower;
	}
}