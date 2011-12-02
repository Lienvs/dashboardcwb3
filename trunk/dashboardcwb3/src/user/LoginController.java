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
		boolean id = false;
		boolean pass = false;
		boolean result = false;
		User selected = null;
		
		if(userName!=null&&password!=null){
			try{
				selected = 	UserManager.getInstance().getUser(userName);
				id=true;
				if (id){		
					if(selected.getPassword().equals(password)) {
						pass = true;
					}
					if(pass) {
						result = true;
						UserManager.getInstance().setCurrentUser(selected);
					}		
				}
				
			}
			catch (JDOObjectNotFoundException e){
				result = false;
			}
			
			
		}
		return result;
		}
		
	/**
	 * Logt de gebruiker uit: stelt de huidige gebruiker in als nul.
	 */
	public void logout() {
		UserManager.getInstance().setCurrentUser(null);	
	}
	
	
}