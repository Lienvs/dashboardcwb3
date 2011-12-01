package user;
import java.util.ArrayList;


public class Professor {
private String profName;
	
	public Professor(String name){
		profName= name;
		
	}
	
	public String getName(){
		return "Prof. " + profName;
	}
	
}
