package activity;

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
}
