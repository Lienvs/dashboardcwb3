package activity;
import java.util.*;
import java.text.*;

import user.User;
import user.UserManager;

/**
 * Klasse met timer functies
 * @author 
 * @version
 *
 */
public class TimerController {
	private Activity currentActivity;
	private String dateFormat;
	
	/**
	 * Constructor
	 */
	
	
	public TimerController(){
		User currentUser =UserManager.getInstance().getCurrentUser();
		
		for(Activity act : currentUser.getActivities()){
			if(act.getStop()==null){
				currentActivity = act;
			}
			else{currentActivity = null;}
		}
		
		
	}
	
	/**
	 * begint het timen van de huidige activiteit.Er wordt een Calendar object aangemaakt en door daar de tijd van op te vragen krijgen we de tijd die het op dat moment is.
	 * die tijd slaan we op in de startdatum van de activiteit (elke activiteit heeft een start- en stop datum).
	 * @param act : de activiteit die we willen timen. (type: Activity)
	 */
	public void startTiming(Activity act){
		currentActivity = act;
		Calendar cal = Calendar.getInstance();
		currentActivity.setStart(cal.getTime());
		
		
	}
	
	/**
	 * be�indigt het timen van de huidige activiteit (=currentActivity).Er wordt een Calendar object aangemaakt en door daar de tijd van op te vragen krijgen we de tijd die het op dat moment is.
	 * die tijd slaan we op in de stopdatum van de activiteit (elke activiteit heeft een start- en stop datum). In deze methode wordt de huidige activiteit ook op nul gezet.
	 * 
	 */
	public void stopTiming(){
		
		Calendar cal = Calendar.getInstance();
		getCurrentActivity().setStop(cal.getTime());
		currentActivity = null;
	}
	
	/**
	 *methode die toelaat om het timen te starten op een moment verschillend van het huidige moment. bijvoorbeeld als na de les nog wilt ingeven dat men naar de les is geweest.
	 *@param month (type: int)
	 *@param day (type: int)
	 *@param hour (type: int)
	 *@param minute (type: int)
	 *@param act : de activiteit waarvan men de startduur wil wijzigen (type: Activity)
	 * 
	 */
	public void setStartTiming(int month, int day, int hour, int minute, Activity act){
		String aMonth; String aDay; String anHour; String aMinute;
		
		if(month<10){aMonth = "0"+month;} 
		else{aMonth = month+"";}
		if(day<10){aDay = "0"+day;}
		else{aDay = day+"";}
		if(hour<10){anHour = "0"+hour;}
		else{anHour = hour+"";}
		if(minute<10){aMinute = "0"+minute;}
		else{aMinute = minute+"";}
		Date begin = changeDate(aMonth+":"+aDay+":"+anHour+":"+aMinute+":");
		act.setStart(begin);
	}
	
	/**
	 *methode die toelaat om het timen te stoppen op een moment verschillend van het huidige moment. bijvoorbeeld als na de les nog wilt ingeven dat men naar de les is geweest.
	 *@param month (type: int)
	 *@param day (type: int)
	 *@param hour (type: int)
	 *@param minute (type: int)
	 *@param act : de activiteit waarvan men de stopduur wil wijzigen (type: Activity)
	 * 
	 */
	public void setStopTiming(int month, int day, int hour, int minute, Activity act){
		String aMonth; String aDay; String anHour; String aMinute;
		
		if(month<10){aMonth = "0"+month;}
		else{aMonth = month+"";}
		if(day<10){aDay = "0"+day;}
		else{aDay = day+"";}
		if(hour<10){anHour = "0"+hour;}
		else{anHour = hour+"";}
		if(minute<10){aMinute = "0"+minute;}
		else{aMinute = minute+"";}
		Date stop = changeDate(aMonth+":"+aDay+":"+anHour+":"+aMinute+":");
		act.setStop(stop);
	}
	
	/**
	 * controleert of er al dan niet een activiteit bezig is
	 * @return isBusy (type: boolean)
	 */
	public boolean isBusy(){
		boolean isBusy = false;
		User currentUser =UserManager.getInstance().getCurrentUser();
		
		for(Activity act : currentUser.getActivities()){
			if(act.getStop()==null){
				isBusy=true;
			}
		}	
		return isBusy;
	
	}
	/**
	 * geeft de activiteit die op dat moment bezig is weer. 
	 * @return currentActivity (type: Activity)
	 */
	public Activity getCurrentActivity(){
		currentActivity = null;
		User user = UserManager.getInstance().getCurrentUser();
		for (Activity act: user.getActivities()){
			if(act.getStop() == null){
				currentActivity = act;
			}
		}
		return currentActivity;
	}
	
	/**
	 * methode die van een String object een datumobject maakt. 
	 * @param dateString (type: String)
	 * @return newDate (type: Date)
	 */
	 private Date changeDate(String dateString) {
	        dateFormat = "MM:DD:HH:mm"; // om de vorm waarin je de tijd moet ingeven te tonen
	        
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); // maakt een SDF object aan
	        ParsePosition pos = new ParsePosition(0); // begint dateFormat op te splitsen vanaf 0
	        Date date = sdf.parse(dateString, pos);// date object wardat dateString opgesplitst w vanaf positie 0
	        Calendar parsedCal = Calendar.getInstance(); // nieuw Calendar object: parsedCal
	        parsedCal.setTime(date); // de tijd wordt ingesteld mbv opgesplitste Date objec (=date)
	        Calendar newCal = Calendar.getInstance();// nieuw Calendar object : newCal
	        newCal.set(Calendar.MONTH, parsedCal.get(Calendar.MONTH));
	        newCal.set(Calendar.DAY_OF_WEEK, parsedCal.get(Calendar.DAY_OF_WEEK));
	        newCal.set(Calendar.HOUR_OF_DAY, parsedCal.get(Calendar.HOUR_OF_DAY));// uren worden ingesteld: gebruik opgesplitste uren van object date
	        newCal.set(Calendar.MINUTE, parsedCal.get(Calendar.MINUTE));// minuten worden ingesteld : gebruik opgesplitste minuten van object date
	        Date newDate = newCal.getTime();// Nieuw Date object w aangemaakt: newDate. Krijgt de Uren en Minuten van objec newCal
	        return newDate;
	 }
	 
}

