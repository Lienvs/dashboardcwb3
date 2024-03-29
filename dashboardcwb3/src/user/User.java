package user;

import java.util.*;



import activity.Activity;
import activity.CurricularActivity;



import course.Course;
import course.CourseManager;



/**
 * klasse die de gebruiker definieert
 * @author
 * @version
 *
 */



public class User {
	
	private ArrayList<Course> myCourses;
	
    private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String gender;
	
	private String rNumber;
	
	private ArrayList<Activity> myActivities;
	
	private HashMap<Course,Integer> goal;
	
	private HashMap<Date,HashMap<Course,Integer>>goals;
    private Boolean hulpBoolean;
    
    
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
		
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.rNumber = rNumber;
		myActivities = new ArrayList<Activity>(); 
		myCourseNames = new ArrayList<String>();
		goal = new HashMap<Course, Integer>();//Integer is aantall MINUTEN!
		goals = new HashMap<Date,HashMap<Course,Integer>>();//date is starttijd van die week
		hulpBoolean = false;
	}
	public void setGoal(Date date, Course course, int min){
		if(goal.containsKey(course)){
			goal.remove(course);
		}
		goal.put(course,min);
		updateGoals(date,goal);
	}
	private void updateGoals(Date date, HashMap<Course,Integer> map){
		if(goals.containsKey(date)){
			goals.remove(date);
		}
		goals.put(date,map);
	}
	public HashMap<Date,HashMap<Course,Integer>> getGoals(){
		return goals;
	}
	public HashMap<Course,Integer> getGoal(Date date){
		return goals.get(date);
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
			myCourseNames.add(course.toString());	
		
	}
	
	/**
	 * verwijdert vak en verwijdert al de activiteiten die gerelateerd zijn aan dat vak.
	 * @param course
	 */
	public void removeCourse(Course course){
		myCourses.remove(course);
		myCourseNames.remove(course.toString());
		for(Activity act: myActivities){
			if(act.toString().contains(course.toString())){
				myActivities.remove(act);
			}
		}
	}
	
	
	public ArrayList<Course> getCourses(){
		myCourses = new ArrayList<Course>();
		
		for(String name: myCourseNames){
			//myCourses.add(CourseManager.getInstance().getCourse(name));
		}	
		return myCourses;
	}
	
	public ArrayList<String> getCourseNames(){
		
		return myCourseNames;
	}
	
	
}
