package activity;
import java.util.*;

import course.Course;


public class Practice extends CurricularActivity{
	
	public Practice(Course course) {
		super(course,"Oefenzitting");
		
//		UserManager.getInstance().sdjfg();
	}
	public void submitVragenLijst(String place, String studyType, String comment, int rating){
		if(comment!=null){
			postComment(comment);
		}
		if(rating!=-1){
		setRating(rating);}
		
	}
	
}