package activity;
import java.util.*;

import user.User;

import course.Course;

/**
 * subklasse van de superklasse Curricular. Stelt de zelfstudie activiteit van de gebruiker voor.
 * @author 
 * @version
 */
public class IndividualStudy extends CurricularActivity {
	
	
	private StudyLocation location;
	private StudyType studyType;
	private ArrayList<User> studieGenoten;
	
	/**
	 * Constructor
	 * @param course : het vak dat wordt verwerkt gedurende scholaire activiteit (type: Course)
	 * @param location : de plaats waar de gebruiker aan zelfstudie doet, er kan gekozen worden tussen de objecten van de enumeratieklasse StudyLocation  (type: StudyLocation)
	 * @param studyType : type zelfstudie dat de gebruiker uitoefent, ook hier gaat het om types uit de enumeratieklasse StudyType (type: StudyType)
	 */
	public IndividualStudy(Course course) {// location en studyType als parameter meegeven zodat programma vraagt welk van,theorie/oefening en waar?
		super(course,"Zelfstudie");
		this.location=location;
		this.studyType=studyType;
		studieGenoten = new ArrayList<User>();
	}
	
	/**
	 * verandert de studielocatie.
	 * @param x (type: StudyLocation)
	 */
	public void setLocation(StudyLocation x) {
		location = x;
	}
	
	/**
	 * verandert het studietype
	 * @param x (type: StudyType)
	 */
	public void setType(StudyType x) {
		studyType = x;
	}
	
	/**
	 * geeft een Stringweergave van de studielocatie weer.
	 * het is onnodig om een methode te maken die het hele object van het type StudyLocation weergeeft want de enigste methode van zo'n object is de toString() methode.
	 * @return location.toString() : een string met de naam van de locatie (type: String)
	 */
	public String getLocation() {		
		return location.toString();
	}
/**
 * geeft een Stringweergave van het studietype weer
 * @return studyType.toString() : een string met de naam van het studietype (type: String)
 */
	public String getStudyType() {
		return studyType.toString();
	}
	public void submitVragenLijst(String place, String studyType, String comment, int rating){
		location = StudyLocation.getStudyLocation(place);
		this.studyType = StudyType.getStudyType(studyType);
		postComment(comment);
		setRating(rating);
	}
	public void addStudyBuddy(User user){
		studieGenoten.add(user);
	}
	public ArrayList<User> getStudyBuddys(){
		return studieGenoten;
	}
}
