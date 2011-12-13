package activity;
import java.util.*;

import user.UserManager;

import course.Course;


public class Practice extends CurricularActivity{
	
	public Practice(Course course) {
		super(course,"Oefenzitting");
		
//		UserManager.getInstance().sdjfg();
	}
	public void submitVragenLijst(String place, String studyType, String comment, int rating,String studybuddy){
		if(comment!=null){
			postComment(comment);
		}
		if(rating!=-1){
		setRating(rating);}
		
	}
	public String getType(){
		return "Oefenzitting";
	}
	public String toString(){
		String result="You are attending a practice of " + getCourse().toString();
		if(UserManager.getInstance().getAmountUsers(getCourse().toString(), "Oefenzitting")==0){
		
		}
		else if(UserManager.getInstance().getAmountUsers(getCourse().toString(), "Oefenzitting")==1){
			result = result + ". " + "There is " + UserManager.getInstance().getAmountUsers(getCourse().toString(), "les")+ " other student with you.";
		}
		else{
			result = result + ". " + "There are " + UserManager.getInstance().getAmountUsers(getCourse().toString(), "les")+ " other students with you.";
		}
		return result;
	}
}
