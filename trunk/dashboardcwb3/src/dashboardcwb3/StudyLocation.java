package dashboardcwb3;

public enum StudyLocation {
	THUIS {

		@Override
		public String toString() {
			return "Thuis";
		}
		
	}, KOT {

		@Override
		public String toString() {
			return "Kot";
		}
		
	}, BIB {

		@Override
		public String toString() {
			return "Bibliotheek";
		}
		
	};

	public abstract String toString();
	
	public static StudyLocation getStudyLocation(String sl) {
		
		for(StudyLocation sll : StudyLocation.values()) {
			if( sll.toString().equals(sl) ) {
				return sll;
			}
		}
		return null;
		
	}

}