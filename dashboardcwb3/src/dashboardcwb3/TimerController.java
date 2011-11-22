package dashboardcwb3;
import java.util.*;
public class TimerController {
	Activity currentActivity;
	
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
}
