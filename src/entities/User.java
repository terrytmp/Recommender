package entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private Integer id;
	
	private List<User> friends;
	
	public User(Integer id) {
		this.id = id;
		friends = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	public void addFriend(User user) {
		this.friends.add(user);
	}
	
}
