package user;

import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;

import activity.Activity;
import activity.CurricularActivity;

import com.google.appengine.api.datastore.Key;

import course.Course;
import course.CourseManager;

import javax.jdo.annotations.Extension;

/**
 * klasse die de gebruiker definieert
 * @author
 * @version
 *
 */

@PersistenceCapable(detachable="true")

public class User {
	@NotPersistent
	private ArrayList<Course> myCourses;
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
    private ArrayList<String> myCourseNames;
	
    
    
	/**
	 * Constructor
	 * @param userName (type: String)
	 * @param password (type: String)
	 * @param firstName (type: String)
	 * @param lastName (type: String)
	 * @param gender (type: String)
	 * @param rNumber (type: String)
	 */
	public User(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		this.userName = userName;
		this.key = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.rNumber = rNumber;
		myActivities = new ArrayList<Activity>(); 
		myCourseNames = new ArrayList<String>();
		
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
	
	/**
	 * verandert paswoord als paramaeter pass overeenkomt met confirmedPass.
	 * @param password (type: String)
	 */
	public void setPassword(String pass, String confirmedPass){
		if(pass.equals(confirmedPass)){
			password = confirmedPass;
		}
	}
	
	/**
	 * Kent activiteiten toe aan de gebruiker. 
	 * @param x (type: Activity)
	 */
	public void addActivity(Activity x) {
		myActivities.add(x);
	}
	
	/**
	 * verwijdert activiteiten uit de activiteitenlijst van de gebruiker
	 * @param x (type: Activity)
	 * @return check : of de activiteit al dan niet verwijderd is (type: boolean)
	 */
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
	
	/**
	 * geeft het aantal minuten dat de gebruiker voor een bepaald vak heeft gewerkt volgens het type 'les', 'zelfstudie', 'oefenzitting'
	 * @param course : het vak waarvoor de gebruiker het aantal minuten wil weten (type: Course)
	 * @param type (type: String)
	 * @return resultaat (type: int)
	 */
	public int getTotalCurricularActivity(Course course, String type){ 
		int i = 0;
		int resultaat = 0;
		while(i<myActivities.size()){
			Activity act = myActivities.get(i);
			if(act.getActivityType().equals("scolair")){
				CurricularActivity scol = (CurricularActivity) act;
				if(scol.getCourse().equals(course) && scol.getType().equals(type)){
					resultaat = resultaat+scol.getDuration();
					
				}
			}
		i++;}
		return resultaat;
	}
	
	public int getTotalExtraCurricularActivity()
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
	
	/**
	 * voegt vak toe
	 * @param course
	 */
	public void addCourse(Course course){
		//try{
		//	pm.currentTransaction().begin();
			myCourseNames.add(course.toString());	
		//}
		//finally{
					
		//}
	}
	
	/**
	 * verwijdert vak en verwijdert al de activiteiten die gerelateerd zijn aan dat vak.
	 * @param course
	 */
	public void removeCourse(Course course){
		myCourses.remove(course);
		for(Activity act: myActivities){
			if(act.toString().contains(course.toString())){
				myActivities.remove(act);
			}
		}
	}
	
	
	public ArrayList<Course> getCourses(){
		myCourses = new ArrayList<Course>();
		
		for(String name: myCourseNames){
			myCourses.add(CourseManager.getInstance().getCourse(name));
		}	
		return myCourses;
	}
	
	public ArrayList<String> getCourseNames(){
		
		return myCourseNames;
	}
	
}
