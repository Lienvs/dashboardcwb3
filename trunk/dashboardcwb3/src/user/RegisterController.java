package user;

/**
 * Klasse die zorgt voor het registreren van de gebruiker.
 * @author 
 * @version
 *
 */
public class RegisterController {
	
/**
 * Controller
 */
	public RegisterController(){
		
	}
	
	/**
	 * registreert een nieuwe gebruiker.
	 * ingegeven userName mag nog niet opgeslagen zijn in de databank.
	 * ingegeven rNummer mag nog niet opgeslagen zijn in de databank.
	 * het confirmedPassword moet overeenkomen met het password.
	 * Alle velden moeten ingevuld zijn
	 * 
	 * @param userName
	 * @param password
	 * @param confirmedPassword
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param rNumber
	 * @return
	 */
	public boolean register(String userName,String password,String confirmedPassword, String firstName, String lastName, String gender, String rNumber) {
		boolean registered = false;
		boolean freeUserName = true;
		boolean freeRNumber = true;
		boolean passwordConfirmed=false;
		boolean allFieldsFilledIn = false;
		
		 //Controle of username en rNummer nog niet bestaan
		 if(!UserManager.getInstance().getUsers().isEmpty()){
			for(User user : UserManager.getInstance().getUsers()){
				if(user.getUserName()!=null&&user.getRNumber()!=null){
					if(user.getUserName().equals(userName)&& freeUserName){
					freeUserName=false;
					}
					if(user.getRNumber().equals(rNumber)&&freeRNumber&&freeUserName){
					freeRNumber=false;					
					}
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
						User user = new User(userName,password,firstName,lastName,gender,rNumber);
						UserManager.getInstance().setCurrentUser(user);
					}		
			
			
		}
		else{ 
		
			//Paswoord en confirmed paswoord moeten gelijk zijn
			if(password.equals(confirmedPassword)){
				passwordConfirmed=true;
			}
			
			//Controleer of alle velden ingevuld zijn
			if(userName!=null&&password!=null&&firstName!=null&&lastName!=null&&gender!=null&&rNumber!=null){
				allFieldsFilledIn = true;
			}
			
			//Als alle controles positief blijken, maak nieuwe user aan
			if(passwordConfirmed&&allFieldsFilledIn){
				registered = true;
				User user = new User(userName,password,firstName,lastName,gender,rNumber);
				
				UserManager.getInstance().setCurrentUser(user);
			}
			
		
			
		 } 
		return registered;
	}

}
