package activity;
import java.util.*;
import java.text.*;

public class TimerController {
	private Activity currentActivity;
	private String dateFormat;
	public TimerController(){
		currentActivity = null;
		
	}
	public void startTiming(Activity act){
		currentActivity = act;
		Calendar cal = Calendar.getInstance();
		currentActivity.setStart(cal.getTime());
		
		
	}
	public void stopTiming(){
		
		Calendar cal = Calendar.getInstance();
		currentActivity.setStop(cal.getTime());
		
		currentActivity = null;
	}
	
	
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
	
	public boolean isBusy(){
		boolean isBusy = false;
		if(currentActivity == null){
			
		}
		else{isBusy = true;}
		return isBusy;
	}
	public Activity getCurrentActivity(){
		return currentActivity;
	}
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
}}

