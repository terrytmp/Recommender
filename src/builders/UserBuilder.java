package builders;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import entities.User;

public class UserBuilder {

	private static String filePath = "data/user_friends.dat";
	private User user;
	
	
	public UserBuilder(User user) {
		this.user = user;
	}
	
	@SuppressWarnings("deprecation")
	public List<User> buildFriendList() throws IOException {
		DataInputStream input = new DataInputStream(new FileInputStream(filePath));
		input.readLine();
        for (int i = 0; i < 100; i++) {
            String line = input.readLine();
            String[] lineArray = line.split("\t");
            if (Integer.parseInt(lineArray[0]) == this.user.getId()) {
            	user.addFriend(new User(Integer.parseInt(lineArray[1])));
            }
        }

        input.close();
		return user.getFriends();
	}
	
	public List<User> buildFriendsFriendList() {
		return null;
	}
	
	public List<User> buildNeighborhood() {
		return null;
	}
}
