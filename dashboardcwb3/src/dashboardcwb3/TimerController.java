package dashboardcwb3;
import java.util.*;
public class TimerController {
	private Activity currentActivity;
	boolean isStopped;
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
}
