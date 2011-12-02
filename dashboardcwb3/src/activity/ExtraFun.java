package activity;

public enum ExtraFun {
	SLEEP {

		@Override
		public String toString() {
			return "Sleep";
		}
		
	},SPORT {

		@Override
		public String toString() {
			return "Sport";
		}
		
	},NIGHTLIFE {

		@Override
		public String toString() {
			return "Nightlife";
		}
	};
		public abstract String toString();
public static ExtraFun getExtraFun(String sl) { //niet echt nuttige methode ? 
		
		for(ExtraFun sll : ExtraFun.values()) {
			if( sll.toString().equals(sl) ) {
				return sll;
			}
		}
		return null;
		
	}


}
