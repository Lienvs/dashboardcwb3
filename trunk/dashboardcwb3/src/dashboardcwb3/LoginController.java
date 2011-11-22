package dashboardcwb3;
import java.util.*;


public class LoginController {



public LoginController(){
	//makeTim();
	
}
public void makeTim() {
	User tim = new Student("tim","mit","a","a","a","a");
	User alex = new Student("alex","ja","a","a","a","a");
	UserManager.getInstance().addUser(tim); UserManager.getInstance().addUser(alex);
}
public boolean login(String userName,String password) {
		boolean id = false;
	    User currentUser = null;
		boolean pass = false;
		boolean result = false;
		User selected = null;
		
		if(UserManager.getInstance().getUsers().size() == 0) {
		}
		else {
			
				for(User user : UserManager.getInstance().getUsers()){
					
				 if (user.getUserName().equals(userName)) {
				id = true;
				selected = user;
				 }
			}
		}
			if (id){		
		if(selected.getPassword().equals(password)) {
			pass = true;
		}
		if(pass) {
			result = true;
			UserManager.getInstance().setCurrentUser(selected);;
		}	}	
	
		
		return result;	
	}
	
	public void logout() {
		UserManager.getInstance().setCurrentUser(null);	
	}
	
	
}