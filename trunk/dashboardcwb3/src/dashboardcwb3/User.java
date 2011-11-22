package dashboardcwb3;

import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

@PersistenceCapable
@Inheritance (strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class User {

	@Persistent
	private String userName;
	@Persistent
	private String password;
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;
	@Persistent
	private String gender;
	@Persistent
	private String rNumber;
	
	public User(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.rNumber = rNumber;	
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	public String getRNumber() {
		return rNumber;
	}
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	
	
}
