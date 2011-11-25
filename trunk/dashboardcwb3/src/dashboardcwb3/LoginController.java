package dashboardcwb3;
import java.util.*;


public class LoginController {

public LoginController(){

}

public boolean login(String userName,String password) {
		boolean id = false;
		boolean pass = false;
		boolean result = false;
		User selected = null;
			
		for(User user : UserManager.getInstance().getUsers()){	
			if (user.getUserName().equals(userName)) {
				id = true;
				selected = user;
			}
		}
			if (id){		
				if(selected.getPassword().equals(password)) {
					pass = true;
				}
				if(pass) {
					result = true;
					UserManager.getInstance().setCurrentUser(selected);
				}		
			}	
		return result;	
	}
	
	public void logout() {
		UserManager.getInstance().setCurrentUser(null);	
	}
	
	
}