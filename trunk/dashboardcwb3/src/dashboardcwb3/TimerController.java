package dashboardcwb3;
import java.util.*;
public class TimerController {
	
	boolean isBusy;
	public TimerController(){
		
		isBusy = false;
	}
	public void startTiming(Activity act){
		Activity currentActivity = act;
		Calendar cal = Calendar.getInstance();
		currentActivity.setStart(cal.getTime());
		isBusy = true;
		
	}
	public void stopTiming(Activity act){
		Activity currentActivity = act;
		Calendar cal = Calendar.getInstance();
		currentActivity.setStop(cal.getTime());
		isBusy = false;
		currentActivity = null;
	}
	public boolean isBusy(){
		return isBusy;
	}
}
