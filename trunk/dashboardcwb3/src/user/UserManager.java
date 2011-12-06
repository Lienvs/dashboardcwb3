package user;

import java.util.*;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;

import database.PMF;

import javax.jdo.Query;
import javax.jdo.Extent;

/**
 * Singleton klasse die de gebruikers beheert.
 * @author 
 * @version
 *
 */
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
	/**
	 * Constructor
	 */
	public UserManager() {
		pm = PMF.get().getPersistenceManager();
		users = new ArrayList<User>();
	}
	
	
	public void addUser(User user){
		makePers(user);
	}
	
	public void deleteUser(String userName){
		pm.deletePersistent(userName);
	}
	
	public User getUser(String userName){ 
		User gezocht = pm.getObjectById(User.class, userName);
		return gezocht;
	}
	
	public ArrayList<User> getUsers(){
		Extent<User> extent = pm.getExtent(User.class);
	    for (User u : extent) {
	        users.add(u);
	    }
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
			pm.flush();
		}
	}
	
	public void updateUser(){
		//Kan user zijn gebruikersnaam/passwoord/... nog veranderen?
		//nog methode updateCourses-> in user??!
	}
	public String getAllUsers(){
		String result = "[";
		Iterator it = users.iterator();
		while(it.hasNext()){
			result = result + it.next().toString();
			if(it.hasNext()){
				result= result+",";
			}
			
		}
		result = result + "]";
		return result;
	}
}
