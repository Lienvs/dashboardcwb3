package user;

import java.util.ArrayList;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import course.Course;
import database.PMF;

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
	
	public void updateUserCourses(){
		try{
			pm.currentTransaction().begin();
			currentUser.setHulpBoolean();
			pm.currentTransaction().commit();
		}
		finally{
			pm.flush();
		}
	}
		
		
	/*public void updateUser(){
		if(newPassword!=0 && newConfirmedPassword!=null){
			try{
				pm.currentTransaction().begin();
				currentUser.setPassword(newPassword, newConfirmedPassword);
				pm.currentTransaction().commit();
			}
			finally{
				pm.flush();
			}
		}
		if(newUserName!=null){
			try{
				pm.currentTransaction().begin();
				currentUser.setUserName(newUserName);
				pm.currentTransaction().commit();
			}
			finally{
				pm.flush();
			}
		}
	}*/
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


