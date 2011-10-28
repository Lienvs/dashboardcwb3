// import java.util.*;

public abstract class User {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
	private String rNumber;
	
	public User(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		userName = this.userName;
		password = this.password;
		firstName = this.firstName;
		lastName = this.lastName;
		gender = this.gender;
		rNumber = this.rNumber;	
	}
	
	public String getUsername() {
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
	
	
}
