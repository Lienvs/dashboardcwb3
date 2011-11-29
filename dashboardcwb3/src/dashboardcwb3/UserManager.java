package dashboardcwb3;

import java.util.*;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;
import dashboardcwb3.PMF;

// Singleton
public class UserManager {

	private static UserManager instance = null;
	
	
	private ArrayList<User> users;
	private User currentUser;
	public PersistenceManager pm;
	
	public static UserManager getInstance() {
		if( instance == null ) {
		
			instance = new UserManager();
		}
		return instance;
	}
	
	public UserManager() {
		// Vraag alle Users op uit de database en voeg toe aan de list van users.
		pm = PMF.get().getPersistenceManager();
		users = new ArrayList<User>();
	}
	
	
	public void addUser(User user){
		users.add(user);
		makePers(user);
	}
	
	public User getUser(String userName){ 
		User gezocht = pm.getObjectById(User.class, userName);
		return gezocht;
	}
	
	public List<User> getUsers(){
		//users = pm.getObjectIdClass(User.class)
		return users;
	}
	
	public void setCurrentUser(User curUser){
		currentUser = curUser;
	}
	public User getCurrentUser(){
		return currentUser;
	}
	
	public void makePers(User user){
		
		try {
			pm.makePersistent(user);
		}
		finally {
//			pm.close();
			pm.flush();
	}
	}
	
}
