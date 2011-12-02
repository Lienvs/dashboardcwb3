package activity;

import java.util.ArrayList;

/**
 * enumeratieklasse die 4 studielocaties definieert. Er kunnen geen andere studielocaties gebruikt worden.
 * @author 
 *
 */
public enum StudyLocation {
	
	HOME { 

		@Override
		public String toString() { // stringweergave van object HOME
			return "Home";
		}
		
	},KOT {

		@Override
		public String toString() {
			return "Kot";
		}
		
	}, LIBRARY {

		@Override
		public String toString() {
			return "Library";
		}
		
	}, OTHER {

		@Override
		public String toString() {
			return "Other";
		}
		
	};

	public abstract String toString();
	
	/**
	 * Vraagt een studylocation op. Als de gebruiker een string ingeeft die overeenkomt met een van de studielocaties, dan wordt die studielocatie weergegeven
	 * @param sl
	 * @return
	 */
	public static StudyLocation getStudyLocation(String sl) { //niet echt nuttige methode ? 
		
		for(StudyLocation sll : StudyLocation.values()) {
			if( sll.toString().equals(sl) ) {
				return sll;
			}
		}
		return null;
		
	}
	
	public static ArrayList<StudyLocation> getStudyLocationAsList(){
		ArrayList<StudyLocation> a = new ArrayList<StudyLocation>();
		a.add(StudyLocation.HOME);
		a.add(StudyLocation.KOT);
		a.add(StudyLocation.LIBRARY);
		a.add(StudyLocation.OTHER);
		return a;
		}	


}