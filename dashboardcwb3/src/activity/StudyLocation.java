package activity;

public enum StudyLocation {
	
	HOME {

		@Override
		public String toString() {
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
	 * om studylocation op te vragen
	 * @param sl
	 * @return
	 */
	public static StudyLocation getStudyLocation(String sl) {
		
		for(StudyLocation sll : StudyLocation.values()) {
			if( sll.toString().equals(sl) ) {
				return sll;
			}
		}
		return null;
		
	}

}