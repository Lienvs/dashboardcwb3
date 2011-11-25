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
public int getDuurOefenzitting(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Oefenzitting")){
				result = result + scol.getDuration(); 
			}
		}
	}
	return result;

}
public int getDuurExtraScolair(){
	ArrayList<Activity> actList = currentUser.getActivities();
	int result = 0;
	for(Activity act : actList){
		if(act.getGenre().equals("extra-scolair")){
			
				result = result + act.getDuration(); 
			}
		
	}
	return result;

}
public int getDuurThuisZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Zelfstudie")){
				Zelfstudie zelf= (Zelfstudie) scol;
				if(zelf.getLocation().toString().equals("Thuis")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getDuurBibZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Zelfstudie")){
				Zelfstudie zelf= (Zelfstudie) scol;
				if(zelf.getLocation().toString().equals("Bibliotheek")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}
public int getDuurKotZelfstudie(){
	int result = 0;
	ArrayList<Activity> actList = currentUser.getActivities();
	for(Activity act : actList){
		if(act.getGenre().equals("scolair")){
			Scolair scol = (Scolair)act;
			if(scol.getType().equals("Zelfstudie")){
				Zelfstudie zelf= (Zelfstudie) scol;
				if(zelf.getLocation().toString().equals("Kot")){
					 result = result+ zelf.getDuration();
				}
			}
		}
	} return result;
}

}
