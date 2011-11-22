package dashboardcwb3;
import java.util.*;

public class Les extends Scolair {
	private int rowNumber;//rij in de aula
	
	public Les(Course course) {
		super(course,"les");
	}
	
	public void setRowNumber(int x) {
		rowNumber = x;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
}
