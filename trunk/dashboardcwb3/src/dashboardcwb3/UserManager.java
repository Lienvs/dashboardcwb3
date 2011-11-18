package dashboardcwb3;

import java.util.List;

// Singleton
public class UserManager {

	private static UserManager instance = null;
	
	private List<User> users;
	private User currentUser;
	public static UserManager getInstance() {
		if( instance == null ) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public UserManager() {
		// Vraag alle Users op uit de database en voeg toe aan de list van users.
		getUsers();
	}
	
	public void makeTim() {
		User tim = new Student("tim","mit","a","a","a","a");
		User alex = new Student("alex","ja","a","a","a","a");
		addUser(tim); addUser(alex);
	}
	public void addUser(User user){
		users.add(user);// database-shizzle
	}
	
	public User getUser(int plaatsInRij)
	{
		return users.get(plaatsInRij);
	}
	public List<User> getUsers(){
		return users;
	}
	
	public void setCurrentUser(User curUser){
		currentUser = curUser;
	}
	public User getCurrentUser(){
		return currentUser;
	}
	
}
