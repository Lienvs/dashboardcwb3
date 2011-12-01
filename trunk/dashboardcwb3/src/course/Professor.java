package course;
import java.util.ArrayList;

/**
 * klasse die de professor van een vak voorstelt.
 * @author 
 *
 */

public class Professor {
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
