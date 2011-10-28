import java.util.*;

public class ZS extends Study {
	
	private String location;
	private String what; // theorie / oefeningen
	
	public ZS(Date start) {
		super(start,"ZS");
	}
	
	public void setLocation(String x) {
		location = x;
	}
	
	public void setWhat(String x) {
		what = x;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getWhat() {
		return what;
	}
}
