package dashboardcwb3;

public class RegisterController {
	
	public RegisterController(){
		
	
	}
	
	
	public boolean register(String userName,String password,String confirmedPassword, String firstName, String lastName, String gender, String rNumber) {
		boolean registered = false;
		boolean freeUserName = true;
		boolean freeRNumber = true;
		
		boolean passwordConfirmed=false;
			for(User user : UserManager.getInstance().getUsers()){
				if(user.getUserName().equals(userName)&& freeUserName){
					freeUserName=false;
				}
				if(user.getRNumber().equals(rNumber)&&freeRNumber&&freeUserName){
					freeRNumber=false;
				}
			}
			if(password.equals(confirmedPassword)){
				passwordConfirmed=true;
			}
		if(freeUserName&&freeRNumber&&passwordConfirmed){
			registered=true;
			User user=new Student("userName","password","firstName","lastName","gender","rNumber");
			UserManager.getInstance().addUser(user);
			UserManager.getInstance().setCurrentUser(user);
		}
		
			
		return registered;
	}

}
