package ua.lviv.lgs.web;

import java.util.ArrayList;
import java.util.List;

public class UserService {

	List<User> userList = new ArrayList<>();
	private static UserService userService;

	private UserService() {
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	public List<User> getList() {
		return userList;
	}

	public User getUser(String email) {
		return userList.stream().filter(u -> u.getEmail().equals(email)).findAny().orElse(null);
	}

	public void saveUser(User user) {
		userList.add(user);
	}

}
