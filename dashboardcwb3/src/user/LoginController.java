package user;
import java.util.*;

import javax.jdo.JDOObjectNotFoundException;

/**
 * Klasse die de loginfuncties aanmaakt.
 * @author 
 *
 */
public class LoginController {

	/**
	 * Controller
	 */
public LoginController(){

}
/**
 * Inloggen van de gebruiker.
 * De parameters (userName en password) mogen niet leeg zijn
 * de username moet in de databank worden teruggevonden, de databank mag niet leeg zijn.
 * het paswoord moet gelijk zijn aan het paswoord horende bij de username.
 * @param userName (type: String)
 * @param password (type: String)
 * @return result (type: boolean)
 */
	public boolean login(String userName,String password) {
		boolean result = false;
		if(UserManager.getInstance().exist(userName)){
			if (UserManager.getInstance().getPassword(userName).equals(password)){
				result = true;
				UserManager.getInstance().setCurrentUserName(userName);
			}
			
		}
		else{
			result = false;
		}		
		return result;
		}
		
	/**
	 * Logt de gebruiker uit: stelt de huidige gebruiker in als nul.
	 */
	public void logout() {
		UserManager.getInstance().setCurrentUserName(null);	
	}
	
	
}