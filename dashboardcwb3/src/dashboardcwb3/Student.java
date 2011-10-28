package dashboardcwb3;
import java.util.*;

public class Student extends User {
    private ArrayList<Activity> myActivities;
    
	public Student(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		super(userName,password,firstName,lastName,gender,rNumber);
		myActivities = new ArrayList<Activity>(); 
	}
	
	public void addActivity(Activity x) {
		myActivities.add(x);
	}
	
	public boolean removeActivity(Activity x) {
		boolean check = false;
		int i = 0;
		while (i <= myActivities.size() && !check) {
			if(myActivities.get(i) == x) {
				myActivities.remove(i);
				check = true;
			}
			i++;
		}
		return check;
	}
	
	
}
