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
	public String register(String userName,String password,String confirmedPassword, String firstName, String lastName, String gender, String rNumber) {
		String registered = "leeg";
		boolean freeUserName = true;
		boolean freeRNumber = true;
		boolean passwordConfirmed=false;
		boolean allFieldsFilledIn = false;
		
		 //Controle of username nog niet bestaat		
		if (UserManager.getInstance().exist(userName)){
			freeUserName = false;
			registered = "userName";
		}
		
		//Controle of rmummer nog niet bestaat
		if(UserManager.getInstance().getRnumbers().contains(rNumber))	{
			freeRNumber = false;
			registered = "rNumber";
		}
		
		//Paswoord en confirmed paswoord moeten gelijk zijn
		if (password.equals(confirmedPassword)){
			passwordConfirmed=true;
		}
		else if(!password.equals(confirmedPassword)){
			registered = "password";
			}
		
		//Controleer of alle velden ingevuld zijn
		if(userName!=""&password!=""&firstName!=""&lastName!=""&gender!=""&rNumber!=""){
			allFieldsFilledIn = true;
		}	
		else if(!(userName!=""&password!=""&firstName!=""&lastName!=""&gender!=""&rNumber!="")){
			registered = "fields";
		}
		
		//Als alle controles positief blijken, maak nieuwe user aan
		if(freeUserName&&passwordConfirmed&&allFieldsFilledIn&&freeRNumber){
			User user = new User(userName,password,firstName,lastName,gender,rNumber);
			UserManager.getInstance().setCurrentUserName(user.getUserName());
			UserManager.getInstance().addUser(user);
			registered = "ok";
		}	
		return registered;
	}

}
