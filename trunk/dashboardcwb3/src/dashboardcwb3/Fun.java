package dashboardcwb3;
import java.util.*;

public class Fun extends Activity {

private String place;
private String description;


public Fun(Date start,String place){
	super(start,"fun");
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
}
