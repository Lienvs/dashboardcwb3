package dashboardcwb3;
import java.util.*;

public class UserManagement 
{
	
	private ArrayList<User> loglist;
	private User currentUser;
	
	public UserManagement() {
		loglist = new ArrayList<User>();
		
		makeTim();
		currentUser = null;
	}
	
	public void makeTim() {
		User tim = new Student("tim","mit","a","a","a","a");
		User alex = new Student("alex","ja","a","a","a","a");
		loglist.add(tim); loglist.add(alex);
	}
	
	public boolean login(String userName,String password) {
	boolean id = false;
		boolean pass = false;
		boolean result = false;
		int selected = 0;
		int i = 0;
		if(loglist.size() == 0) {
		}
		else {
			while(i < loglist.size() && !id) {
			if (loglist.get(i).getUserName().equals(userName)) {
				id = true;
			selected = i;
			}
			i++;
		}
		if(loglist.get(selected).getPassword().equals(password) && id) {
			pass = true;
		}
		if(id && pass) {
			result = true;
			currentUser = loglist.get(selected);
		}		
	}
		return result;	
	}
	
	public void logout() {
		currentUser=null;	
	}
	
	public boolean register(String userName,String password,String confirmedPassword, String firstName, String lastName, String gender, String rNumber) {
		boolean registered = false;
		boolean freeUserName = false;
		boolean freeRNumber = false;
		boolean passwordConfirmed=false;
			for(User user : loglist){
				if(!user.getUserName().equals(userName)){
					freeUserName=true;
				}
				if(!user.getRNumber().equals(rNumber)){
					freeRNumber=true;
				}
			}
			if(password.equals(confirmedPassword)){
				passwordConfirmed=true;
			}
		if(freeUserName&&freeRNumber&&passwordConfirmed){
			registered=true;
			User user=new Student("userName","password","firstName","lastName","gender","rNumber");
			loglist.add(user);
			currentUser=user;
		}
		
		return registered;
	}
	
	
	
	
	
	
	
	
	



	
}