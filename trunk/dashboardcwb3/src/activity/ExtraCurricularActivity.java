package activity;
import course.Course;

/**
 * Subklasse van de superklasse Activity. Stelt de buitenschoolse activiteiten van de gebruiker voor.
 * @author 
 * @version
 *
 */
public class ExtraCurricularActivity extends Activity {

private String place;

private ExtraFun extra;

/**
 * Constructor
 * @param place
 */
public ExtraCurricularActivity(ExtraFun extra){
	super("fun");
	this.extra=extra;
	place ="";
	
	
}


public void changePlace(String place){
	place=this.place;
}
public String getPlace(){
	return place;
}

private ExtraFun getExtra(){
	return extra;
}
public String toString(){
	String result =  "U doet momenteel een buitenschoolse activiteit:"+getType();
	return result;
	
}
public String getType(){
	ExtraFun ex =  getExtra();
	return ex.toString();
	
}


public void submitVragenLijst(String place, String studyType, String comment, int rating,String studybuddy){
	changePlace(place);
	setRating(rating);
	postComment(comment);
	
}
public Course getCourse(){
return null;
}
}
