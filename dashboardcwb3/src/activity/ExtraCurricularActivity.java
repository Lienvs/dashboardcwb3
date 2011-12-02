package activity;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Subklasse van de superklasse Activity. Stelt de buitenschoolse activiteiten van de gebruiker voor.
 * @author 
 * @version
 *
 */
public class ExtraCurricularActivity extends Activity {

private String place;
private String description;
private ExtraFun extra;

/**
 * Constructor
 * @param place
 */
public ExtraCurricularActivity(ExtraFun extra){
	super("fun");
	this.extra=extra;
	place ="";
	description = "";//Dit kan ng aangepast worden natuurlijk
	
}

public void setDescription(String des){
	description = place + des;
}
public void changePlace(String place){
	place=this.place;
}
public String getPlace(){
	return place;
}
public String getDescription(){
	return description;
	
}
public String toString(){
	String result = "U doet momenteel een buitenschoolse activiteit."+ getDescription();
	return result;
	
}
public String getType(){
	return "Ontspanning";
}
public void submitVragenLijst(String place, String studyType, String comment, int rating){
	changePlace(place);
	setRating(rating);
	postComment(comment);
	
}
}