package dashboardcwb3;

import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Student extends User {
    @Persistent
	private ArrayList<Activity> myActivities;
    private ArrayList<Course> myCourses;
    
	public Student(String userName, String password, String firstName,String lastName, String gender, String rNumber){
		super(userName,password,firstName,lastName,gender,rNumber);
		myActivities = new ArrayList<Activity>(); 
		myCourses = new ArrayList<Course>();
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
			if(act.getGenre().equals("scolair")){
				Scolair scol = (Scolair) act;
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
			if(act.getGenre().equals("fun")){
				resultaat = resultaat+act.getDuration();
			}
		i++;}
		return resultaat;
	
	}
	
	public void addCourse(Course course){
		myCourses.add(course);
	}
	
	public ArrayList<Course> getCourses(){
		return myCourses;
	}
	
	
	
	
}
