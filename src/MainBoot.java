import java.io.IOException;

import builders.UserBuilder;
import entities.User;

public class MainBoot {

	public static void main(String[] args) throws IOException {
		User user = new User(2);
		UserBuilder ub = new UserBuilder(user);
		user.setFriends(ub.buildFriendList());
		System.out.println(user.getFriends().size());
	}
}
