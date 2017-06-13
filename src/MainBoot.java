
import java.io.IOException;
import java.util.Iterator;
import entities.User;

public class MainBoot {

	public static void main(String[] args) throws IOException {
		// Build user
		User.Builder builder = new User.Builder();
		User user = builder.id(1).build();
		
		Iterator<User> it = user.getNeighbourhood().iterator();
		while (it.hasNext()) {
			User currUser = it.next();
			System.out.println("USER ID : " + currUser.getId() + " ARTISTS COUNT : " + currUser.getArtists().size());
		}
		
		System.out.println(user.getNeighbourhood().iterator().next().getArtists());
	} 
}
