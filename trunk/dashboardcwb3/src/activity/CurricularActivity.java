package activity;

import java.util.Date;

import course.Course;

/**
 * Subklasse van de klasse Activity. Stelt de scholaire activiteiten van de gebruiker voor
 * @author 
 * @version
 *
 */
public abstract class CurricularActivity extends Activity{
	private Course course;
	private String type;  //les, Oefenzitting of Zelfstudie, ook niet beter met enumeratieklasse?
	
	/**
	 * Constructor
	 * @param course : het vak dat wordt verwerkt gedurende de scholaire activiteit van de gebruiker (type: Course)
	 * @param type : het type scholaire activiteit, dit kan zijn: "les","oefenzitting","zelfstudie" (type: String)
	 */
	public CurricularActivity(Course course, String type){
		super("scolair"); //"scholair" is het type activiteit
		this.course=course;
		this.type=type;
	}
	
	/**
	 * geeft het vak weer
	 * @return course (type: Course)
	 */
	public Course getCourse(){
		return course;
	}
	
	/**
	 * geeft het type scholaire activiteit weer
	 * @return type (type: String)
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * geeft een samenvatting weer van de activiteit die de gebruiker heeft geselecteerd:
	 * scholair activiteit, het type van de scholaire activiteit en voor welk vak.
	 *  @return result (type: String)
	 */
	public String toString(){
		String result = "U studeert momenteel "+course.toString() + " "+getType() + ".";
		return result;
	}
	public abstract void submitVragenLijst(String place, String studyType, String comment, int rating,String studybuddy);
}
