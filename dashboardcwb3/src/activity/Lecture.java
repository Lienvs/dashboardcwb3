package activity;
import java.util.*;

import course.Course;


public class Lecture extends Curricular {
	private int rowNumber;//rij in de aula
	
	public Lecture(Course course) {
		super(course,"les");
	}
	
	public void setRowNumber(int x) {
		rowNumber = x;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
}
