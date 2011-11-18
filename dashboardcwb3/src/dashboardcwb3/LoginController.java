package dashboardcwb3;
import java.util.*;


public class LoginController {



public LoginController(){
	
	
}
public boolean login(String userName,String password) {
	boolean id = false;
	    User currentUser = null;
		boolean pass = false;
		boolean result = false;
		int selected =0;
		int i = 0;
		if(UserManager.getInstance().getUsers().size() == 0) {
		}
		else {
			while(i < UserManager.getInstance().getUsers().size() && !id) {
			if (UserManager.getInstance().getUser(i).getUserName().equals(userName)) {
				id = true;
			selected = i;
			}
			i++;
		}
		if(UserManager.getInstance().getUser(selected).getPassword().equals(password) && id) {
			pass = true;
		}
		if(id && pass) {
			result = true;
			currentUser = UserManager.getInstance().getUser(selected);
		}		
	}
		UserManager.getInstance().setCurrentUser(currentUser);
		return result;	
	}
	
	public void logout() {
		UserManager.getInstance().setCurrentUser(null);	
	}
	
	
}