package dashboardcwb3;

import java.util.List;

// Singleton
public class UserManager {

	private static UserManager instance = null;
	
	private List<User> users;
	
	public static UserManager getInstance() {
		if( instance == null ) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public UserManager() {
		// Vraag alle Users op uit de database en voeg toe aan de list van users.
	}
	
}
