package dashboardcwb3;
import java.util.*;

public abstract class User {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
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
		password=this.password;
	}
	
	
	
}
