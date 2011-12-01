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

/**
 * Constructor
 * @param place
 */
public ExtraCurricularActivity(String place){
	super("fun");
	this.place=place;
	description = "";//Dit kan ng aangepast worden natuurlijk
	
}

public void setDescription(String des){
	description = des;
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
	String result = "extra-scolair" + " "+ getDescription();
	return result;
	
}
}