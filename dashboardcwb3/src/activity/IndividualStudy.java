package activity;
import java.util.*;

import user.User;
import user.UserManager;

import course.Course;

/**
 * subklasse van de superklasse Curricular. Stelt de zelfstudie activiteit van de gebruiker voor.
 * @author 
 * @version
 */
public class IndividualStudy extends CurricularActivity {
	
	
	private StudyLocation location;
	private StudyType studyType;
	private String studieGenoot;
	
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
		studieGenoot = null;
	}
	
	/**
	 * verandert de studielocatie.
	 * @param x (type: StudyLocation)
	 */
	public void setLocation(StudyLocation x) {
		location = x;
	}
	public StudyLocation getStudyLocation()
	{
		return location;
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
		String result = "";
		if(location!=null){
			result=location.toString();
		}
		return result;
	}
/**
 * geeft een Stringweergave van het studietype weer
 * @return studyType.toString() : een string met de naam van het studietype (type: String)
 */
	public String getStudyType() {
		return studyType.toString();
	}
	public void submitVragenLijst(String place, String studyType, String comment, int rating,String studybuddy){
		location = StudyLocation.getStudyLocation(place);
		this.studyType = StudyType.getStudyType(studyType);
		postComment(comment);
		setRating(rating);
		studieGenoot = studybuddy;
	}
	public void setStudyBuddy(String studybuddy){
		studieGenoot = studybuddy;
	}
	public String getStudyBuddy(){
		return studieGenoot;
	}
	public String getType(){
		return "Zelfstudie";
	}
	public String toString(){
		return "You are studying "+ getCourse().toString() + " right now.";
	}
}
