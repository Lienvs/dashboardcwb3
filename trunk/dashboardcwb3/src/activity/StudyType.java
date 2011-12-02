package activity;
 /**
  * enumeratieklasse die 2 studietypen definieert: theorie en oefeningen. Enkel in deze klasse kunnen nieuwe studietypen aangemaakt worden.
  * @author 
  *
  */
public enum StudyType {
	
	THEORY {

		@Override
		public String toString() {
			return "Theory";
		}
		
	},PRACTICE {

		@Override
		public String toString() {
			return "Practice";
		}
		
	};
public static StudyType getStudyType(String sl) { //niet echt nuttige methode ? 
		
		for(StudyType sll : StudyType.values()) {
			if( sll.toString().equals(sl) ) {
				return sll;
			}
		}
		return null;
		
	}
}
