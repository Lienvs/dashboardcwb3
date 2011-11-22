package dashboardcwb3;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
/*
 * public void changeDate() {
        dateFormat = "HH:mm"; // om de vorm waarin je de tijd moet ingeven te tonen
        dateString = gui.showInputPanel("Geef als volgt een uur in ("+ dateFormat + " )"); // vraagt de gebr om de tijd in te gevenop zelfde manier als dateFormat
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); // maakt een SDF object aan
        ParsePosition pos = new ParsePosition(0); // begint dateFormat op te splitsen vanaf 0
        Date date = sdf.parse(dateString, pos);// date object wardat dateString opgesplitst w vanaf positie 0
        Calendar parsedCal = Calendar.getInstance(); // nieuw Calendar object: parsedCal
        parsedCal.setTime(date); // de tijd wordt ingesteld mbv opgesplitste Date objec (=date)
        Calendar newCal = Calendar.getInstance();// nieuw Calendar object : newCal
        newCal.set(Calendar.HOUR_OF_DAY, parsedCal.get(Calendar.HOUR_OF_DAY));// uren worden ingesteld: gebruik opgesplitste uren van object date
        newCal.set(Calendar.MINUTE, parsedCal.get(Calendar.MINUTE));// minuten worden ingesteld : gebruik opgesplitste minuten van object date
        Date newDate = newCal.getTime();// Nieuw Date object w aangemaakt: newDate. Krijgt de Uren en Minuten van objec newCal
        gui.setTime(newDate);// Tijd wordt heringesteld
        gui.updateSchedule();// schedule wordt geupdated met nieuwe tijd.
    }
 * 
 * 
