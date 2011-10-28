import java.util.*;

public class Les extends Study {
	private int rowNumber;//rij in de aula
	
	public Les(Date start) {
		super(start,"les");
	}
	
	public void setRowNumber(int x) {
		rowNumber = x;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
}
