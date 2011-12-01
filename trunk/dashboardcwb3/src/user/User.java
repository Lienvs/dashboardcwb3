package user;

import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;

import activity.Activity;
import activity.Curricular;

import com.google.appengine.api.datastore.Key;

import course.Course;

import javax.jdo.annotations.Extension;




@PersistenceCapable(detachable="true")
public class User {

	@PrimaryKey
	private String key;
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
	@Persistent
	private ArrayList<Activity> myActivities;
	@Persistent
    private ArrayList<Course> myCourses;
	
	public User(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		this.userName = userName;
		this.key = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.rNumber = rNumber;
		myActivities = new ArrayList<Activity>(); 
		myCourses = new ArrayList<Course>();
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
	
	public ArrayList<Activity> getActivities(){
		return myActivities;
	}
	
	public int getTotalScolair(Course course, String type){ 
		int i =0;
		int resultaat = 0;
		while(i<myActivities.size()){
			Activity act = myActivities.get(i);
			if(act.getActivityType().equals("scolair")){
				Curricular scol = (Curricular) act;
				if(scol.getCourse().equals(course) && scol.getType().equals(type)){
					resultaat = resultaat+scol.getDuration();
					
				}
			}
		i++;}
		return resultaat;
	}
	
	public int getTotalFun()
	{
		int i =0;
		int resultaat = 0;
		while(i<myActivities.size()){
			Activity act = myActivities.get(i);
			if(act.getActivityType().equals("fun")){
				resultaat = resultaat+act.getDuration();
			}
		i++;}
		return resultaat;
	
	}
	
	public void addCourse(Course course){
		myCourses.add(course);
	}
	public void removeCourse(Course course){
		myCourses.remove(course);
		for(Activity act: myActivities){
			if(act.toString().contains(course.toString())){
				myActivities.remove(act);
			}
		}
	}
	
	
	public ArrayList<Course> getCourses(){
		return myCourses;
	}
	
}
