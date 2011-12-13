package activity;

public enum ExtraFun {
	SLEEP {

		@Override
		public String toString() {
			return "Sleep";
		}
		public String toStringEnglish() {
			return "Sleep";
		}
		
	},SPORT {

		@Override
		public String toString() {
			return "Sport";
		}
		public String toStringEnglish() {
			return "Sport";
		}
		
	},NIGHTLIFE {

		@Override
		public String toString() {
			return "Nightlife";
		}
		public String toStringEnglish() {
			return "Nightlife";
		}
	},OTHER {

		@Override
		public String toString() {
			return "Other";
		}
		public String toStringEnglish() {
			return "Other";
		}
	};
		public abstract String toString();
			
		public static ExtraFun getExtraFun(String sl) { 
		
			for(ExtraFun sll : ExtraFun.values()) {
				if( sll.toString().equals(sl) ) {
					return sll;
				}
			}
		return null;
		
		}

		public abstract String toStringEnglish();

}
