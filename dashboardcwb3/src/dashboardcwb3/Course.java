package dashboardcwb3;
//import java.util.*;

//import sun.util.resources.CalendarData_da;

public class Course {

	private Prof prof;
	private int studyPoints;
	private int totalLes;  //aantal uur les van dit vak over hele semester
	private int totalOZ; //aantal uur oefenzitting van dit vak over heel semester
	private String name; //naam van het vak vb: analyse
	
	public Course(int totalLes, int totalOZ, Prof prof, String name, int studyPoints) {
		this.totalLes = totalLes;
		this.totalOZ = totalOZ;
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
	
	public int getTotalLes(){
		return totalLes;
	}
	
	public int getTotalOZ(){
		return totalOZ;
	}
	
	public String getName(){
		return name;
	}
	
}
