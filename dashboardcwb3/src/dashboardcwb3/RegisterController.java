package dashboardcwb3;

public class RegisterController {
	
	public RegisterController(){
		
	
	}
	
	
	public boolean register(String userName,String password,String confirmedPassword, String firstName, String lastName, String gender, String rNumber) {
		boolean registered = false;
		boolean freeUserName = true;
		boolean freeRNumber = true;
		boolean passwordConfirmed=false;
		boolean allFieldsFilledIn = false;
		
		//Controle of username en rNummer nog niet bestaan
			for(User user : UserManager.getInstance().getUsers()){
				if(user.getUserName().equals(userName)&& freeUserName){
					freeUserName=false;
				}
				if(user.getRNumber().equals(rNumber)&&freeRNumber&&freeUserName){
					freeRNumber=false;
				}
			}
			//Paswoord en confirmed paswoord moeten gelijk zijn
			if(password.equals(confirmedPassword)){
				passwordConfirmed=true;
			}
			
		//Controleer of alle velden ingevuld zijn
			if(userName!=null&&password!=null&&firstName!=null&&lastName!=null&&gender!=null&&rNumber!=null){
				allFieldsFilledIn = true;
			}
			
		//Als alle controles positief blijken, maak nieuwe user aan
			if(freeUserName&&freeRNumber&&passwordConfirmed&&allFieldsFilledIn){
				registered = true;
				User user = new Student("userName","password","firstName","lastName","gender","rNumber");
				UserManager.getInstance().addUser(user);
				UserManager.getInstance().setCurrentUser(user);
			}		
		return registered;
	}

}
