package course;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * klasse die de professor van een vak voorstelt.
 * @author 
 *
 */
@PersistenceCapable
public class Professor {
@Persistent
private String profName;
	
/**
 * Constuctor
 * @param name (type: String)
 */
	public Professor(String name){
		profName= name;
		
	}
	
	/**
	 * Geeft de naam van de professor weer
	 * @return "Prof. " + profName : de naam van de professor (type: String)
	 */
	public String getName(){
		return "Prof. " + profName;
	}
	
}
