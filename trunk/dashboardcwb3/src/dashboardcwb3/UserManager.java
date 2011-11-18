package dashboardcwb3;

import java.util.*;

// Singleton
public class UserManager {

	private static UserManager instance = null;
	
	private ArrayList<User> users;
	private User currentUser;
	public static UserManager getInstance() {
		if( instance == null ) {
		
			instance = new UserManager();
		}
		return instance;
	}
	
	public UserManager() {
		// Vraag alle Users op uit de database en voeg toe aan de list van users.
		users = new ArrayList<User>();
	}
	
	
	public void addUser(User user){
		users.add(user);// database-shizzle
	}
	
	public User getUser(String userName)
	{ User gezocht = null;
		for(User user: users){
		if(user.getUserName().equals(userName)){
			gezocht = user;
		}
	}
		return gezocht;
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
