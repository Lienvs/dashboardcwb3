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
		boolean passwordConfirmed=false;
		boolean allFieldsFilledIn = false;
		
		 //Controle of username en rNummer nog niet bestaan
		
		if (UserManager.getInstance().exist(userName) | UserManager.getInstance().getRNumbers().contains(rNumber)){
			freeUserName = false;
		}
		
		
		//Paswoord en confirmed paswoord moeten gelijk zijn
		if (password.equals(confirmedPassword)){
			passwordConfirmed=true;
		}
		
		
		//Controleer of alle velden ingevuld zijn
		if(userName!=null&&password!=null&&firstName!=null&&lastName!=null&&gender!=null&&rNumber!=null){
			allFieldsFilledIn = true;
		}
		
		
		//Als alle controles positief blijken, maak nieuwe user aan
		if(freeUserName&&passwordConfirmed&&allFieldsFilledIn){
			registered = true;
			User user = new User(userName,password,firstName,lastName,gender,rNumber);
			UserManager.getInstance().setCurrentUserName(user.getUserName());
			UserManager.getInstance().addUser(user);
		}	
		return registered;
	}

}
