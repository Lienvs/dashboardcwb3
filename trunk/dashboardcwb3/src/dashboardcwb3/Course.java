package dashboardcwb3;
//import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

//import sun.util.resources.CalendarData_da;

@PersistenceCapable
public class Course {
	@Persistent
	private Prof prof;
	@Persistent
	private int studyPoints;
	@Persistent
	private int totalLecture;  //aantal uur les van dit vak over hele semester
	@Persistent
	private int totalPractice; //aantal uur oefenzitting van dit vak over heel semester
	@Persistent
	private String name; //naam van het vak vb: analyse
	
	public Course(int totalLecture, int totalPractice, Prof prof, String name, int studyPoints) {
		this.totalLecture = totalLecture;
		this.totalPractice = totalPractice;
		this.prof = prof;
		this.name=name;
		this.studyPoints=studyPoints;
	}	
	
	public Prof getProf(){
		return prof;
	}
	
	public int getStudyPoints(){
		return studyPoints;
	}
	
	public int getTotalLecture(){
		return totalLecture;
	}
	
	public int getTotalPractice(){
		return totalPractice;
	}
	
	public String toString(){
		return name;
	}
	public int getAverageLecture(){//per week (in uur)
		return getTotalLecture()/13;
	}
	public int getAveragePractice(){//per week (in uur)
		return getTotalPractice()/13;
	}
	public int getAvarageWork(){//per week (in uur)
		return studyPoints *30 *60/13;
	}
}
