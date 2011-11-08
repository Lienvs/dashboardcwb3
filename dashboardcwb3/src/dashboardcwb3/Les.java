package dashboardcwb3;
import java.util.*;

public class Les extends Scolair {
	private int rowNumber;//rij in de aula
	
	public Les(Course course, Date start) {
		super(course, start,"les");
	}
	
	public void setRowNumber(int x) {
		rowNumber = x;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
}
