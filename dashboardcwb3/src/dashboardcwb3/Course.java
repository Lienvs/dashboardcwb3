package dashboardcwb3;
//import java.util.*;

//import sun.util.resources.CalendarData_da;

public class Course {

	private Prof prof;
	private int studyPoints;
	private int totalLecture;  //aantal uur les van dit vak over hele semester
	private int totalPractice; //aantal uur oefenzitting van dit vak over heel semester
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
	
}
