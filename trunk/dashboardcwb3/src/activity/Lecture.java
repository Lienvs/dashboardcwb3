package activity;
import java.util.*;

import course.Course;

/**
 * subklasse van de superklasse Curricular. Stelt de lesactiviteit voor van de gbruiker.
 * @author 
 * @version
 *
 */
public class Lecture extends CurricularActivity {
	private RowNumber rowNumber;//rij in de aula
	
	/**
	 * Contructor
	 * @param course : het vak dat in de les wordt gegeven (type: Course)
	 * @param row : de rij waar je je bevindt. (Er zijn 15 rijen) (type: RowNumber)
	 */
	public Lecture(Course course) {// rowNumber toevoegen als parameter
		super(course,"les");
	}
	/**
	 * verandert het rijnummer dat de student oorspronkelijk heeft ingegeven.
	 * @param x (type: RowNumber)
	 */
	public void setRowNumber(RowNumber x) {
		rowNumber = x;
	}
	
	/**
	 * geeft een stringweergave weer van de rij waarop de gebruiker zich bevindt.
	 * @return
	 */
	public String getRowNumber() {
		return rowNumber.toString();
	}
	public String getType(){
		return "les";
	}
	public void submitVragenLijst(String place, String studyType, String comment, int rating,String studybuddy){
		
		postComment(comment);
		setRating(rating);
	}
	public String toString(){
		return "You are attending a lecture of " + getCourse().toString();
	}
}
