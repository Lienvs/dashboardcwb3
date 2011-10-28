import java.util.*;

import sun.util.resources.CalendarData_da;

public class Course extends Activity {

	
	private int totalL; 
	private int totalOZ;
	private HashMap<Date,Integer> doelstellingen; // er komt een doelstelling per week...
	private Prof prof;
	private ArrayList<Study> studies;	
	
	public Course(int totalL, int totalOZ, Prof prof) {
		totalL = this.totalL;
		totalOZ = this.totalOZ;
		prof = this.prof;
		doelstellingen = new HashMap<Date,Integer>();
		studies = new ArrayList<Study>();
	}	
	
	public int getTimeL(){
		int time = 0;
		int i = 0;
		while(i < studies.size()) {
			if(studies.get(i).getType().equals("les")) {
				time = time + studies.get(i).getDuration();
			}
			i++;
		}
		return time;
	}
	
	public int getTimeOZ(){
		int time = 0;
		int i = 0;
		while(i < studies.size()) {
			if(studies.get(i).getType().equals("OZ")) {
				time = time + studies.get(i).getDuration();
			}
			i++;
		}
		return time;
	}
	public int getTimeZS(){
		int time = 0;
		int i = 0;
		while(i < studies.size()) {
			if(studies.get(i).getType().equals("ZS")) {
				time = time + studies.get(i).getDuration();
			}
			i++;
		}
		return time;
	}
	
	public HashMap<Date,Integer> getDoelstellingen()
	{
		return doelstellingen;
	}
	
	public ArrayList<Study> getStudies() {
		return studies;
	}
}
