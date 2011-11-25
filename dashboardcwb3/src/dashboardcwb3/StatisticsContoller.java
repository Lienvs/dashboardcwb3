package dashboardcwb3;
import java.util.*;

public class StatisticsContoller {
	User currentUser;
		public  StatisticsContoller(){
			currentUser = UserManager.getInstance().getCurrentUser();
}
public int getDuurStudie(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Zelfstudie")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;
}

public int getDuurLes(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Les")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}


}
