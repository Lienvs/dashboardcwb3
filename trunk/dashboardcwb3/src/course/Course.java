package course;
//import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


//import sun.util.resources.CalendarData_da;

/**
 * Klasse die een vak vertegenwoordigt.
 * @author 
 * @version
 *
 */
@PersistenceCapable
public class Course {
	//@Persistent
	//private String key;
	@Persistent
	private Professor prof;
	@Persistent
	private int studyPoints;
	@Persistent
	private int totalLecture;  //aantal uur les van dit vak over hele semester
	@Persistent
	private int totalPractice; //aantal uur oefenzitting van dit vak over heel semester
	@Persistent
	private String name; //naam van het vak vb: analyse
	
	
	/**
	 * Constructor
	 * @param totalLecture (type: int)
	 * @param totalPractice (type: int)
	 * @param prof (type: Prof)
	 * @param name (type: String)
	 * @param studyPoints (type: int)
	 */
	public Course(int totalLecture, int totalPractice, Professor prof, String name, int studyPoints) {
		this.totalLecture = totalLecture;
		this.totalPractice = totalPractice;
		this.prof = prof;
		this.name = name;
		this.studyPoints = studyPoints;
//		String nm = name.substring(4);
		//this.key = name;
	}	
	
	/**
	 * geeft de prof van het vak weer (als een object)
	 * @return prof (type: Professor)
	 */
	public Professor getProf(){
		return prof;
	}
	
	/**
	 * geeft het anatal studiepunten van het vak weer
	 * @return studyPoints (type: int)
	 */
	public int getStudyPoints(){
		return studyPoints;
	}
	
	/**
	 * geeft het aantal uren les voor het vak weer
	 * @return totalLecture (type: int)
	 */
	public int getTotalLecture(){
		return totalLecture;
	}
	
	/**
	 * geeft het aantal uren oefenzitting voor het vak weer
	 * @return totalPractice (type: int)
	 */
	public int getTotalPractice(){
		return totalPractice;
	}
	
	/**
	 * geeft de naam van het vak weer.
	 * @return name (type: String)
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * geeft het aantal lesuren van het vak per week weer
	 * @return getTotalLecture()/13 (type: int)
	 */
	public int getAverageLecture(){//per week (in uur)
		return getTotalLecture()/13; //er zijn 13 weken in een semester
	}
	
	/**
	 * geeft het aantal oefenzittinguren van het vak per week weer
	 * @return getTotalPractice()/13 (type: int)
	 */
	public int getAveragePractice(){//per week (in uur)
		return getTotalPractice()/13; // er zijn 13 weken in een semester
	}
	
	/**
	 * geeft de vereiste studieuren aan die de student volgens zijn ISP aan het vak moet besteden per week 
	 * @return studyPoints *30 *60/13 (type: int)
	 */
	public int getAvarageWork(){//per week (in uur)
		return studyPoints *30 *60/13; // 30 uren studie per studiepunt , wrm maal 60? 
	}
	
}
