package user;
import java.util.*;


public class LoginController {

public LoginController(){

}

public boolean login(String userName,String password) {
		boolean id = false;
		boolean pass = false;
		boolean result = false;
		User selected = null;
		
		if(userName!=null&&password!=null){
		if (UserManager.getInstance().getUser(userName) != null){
			selected = 	UserManager.getInstance().getUser(userName);
			id=true;
		}
		if (id){		
			if(selected.getPassword().equals(password)) {
				pass = true;
			}
			if(pass) {
				result = true;
				UserManager.getInstance().setCurrentUser(selected);
			}		
		}	}
		return result;	
	}
	
	public void logout() {
		UserManager.getInstance().setCurrentUser(null);	
	}
	
	
}