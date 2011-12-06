package activity;

import java.util.Date;
/**
 * Superklasse die de activiteit van een student voorstelt.
 * @author 
 * @version
 */
public abstract class Activity {
	
	private Date start;
	private Date stop;
	private String comment;
	private int rating;
	
	private String activityType;
	
	/**
	 * Constructor
	 * @param activityType : type van de activiteit, dit kan zijn: "scholair" of "fun". die variabelen heb ik niet naar het engels veranderd omdat ik niet weet hoe dat in de servlets wordt aangeroepen. 
	 */
	public Activity(String activityType) {
		this.start = null;
		this.stop = null;
		
		comment = null;
		rating  = 0;	
		this.activityType = activityType;
	}
	
	/**
	 * Commentaar opslaan in de variabele commentaar.
	 * De waarde die in de variabele comment is opgeslagen wordt met deze methode overschreven.
	 * @param x : commentaar die de gebruiker invoert (type: String)
	 */
	public void postComment(String x) { 
		comment = x; // als er al een comment is, wordt die gewoon overschreven (geen spam)
	}
	/**
	 * Rating opslaan in de variabele rating.
	 * De waarde die in de variabele rating is opgeslagen wordt met deze methode overschreven.
	 * De gebruiker kan slechts een ratingt tussen 0 en 10 geven.
	 * @param x : de rating die de gebruiker invoert (type: int)
	 */
	public void setRating(int x) {
		if(x>=0 && x<=10){
		rating = x; // overschrijven
		}
	}
	
	/**
	 * verandert de stopdatum van een activiteit
	 * @param date
	 */
	public void setStop(Date date) {
		
		stop = date;
	}
	
	/**
	 * verandert de startdatum van een activiteit
	 * @param date
	 */
	public void setStart(Date date) {
		
		start = date;
	}
	
	/**
	 * geeft de start datum van een activiteit weer
	 * @return start : de startdatum (type: Date)
	 */
	public Date getStart() {
		return start;
	}
	
	/**
	 * geeft de stop datum van een activiteit weer
	 * @return stop : de stopdatum (type: Date)
	 */
	public Date getStop() {
		return stop;
	}
	
	/**
	 * berekent en weergeeft de duur van een activiteit. 
	 * de tijd is in miliseconden dus wordt er gedeeld door 60 000 om de tijd in minuten te krijgen
	 * @return duur : tijd in minuten (type: int)
	 */
	public int getDuration() {
		int duur = 0;
		if(stop !=null&&start!=null){
		int i = (int) ((stop.getTime() - start.getTime())/60000);
		duur =  i;}
		if(getActivityType().equals("scolair")){
			if (getType().equals("Les")||getType().equals("Oefenzitting")){
				if(duur>105 && duur< 135){
					duur = 120;
				}
			}
			
		}
		return duur;
	}
	
	/**
	 * geeft comment weer
	 * @return comment (type: String)
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * geeft rating weer
	 * @return rating (type: int)
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * geeft genre  weer
	 * @return genre (type: String)
	 */
	public String getActivityType(){
		return activityType;
	}
	public abstract void submitVragenLijst(String place, String studyType, String comment, int rating,String studyBuddy);
	
	/**
	 * abstracte methode die ervoor zorgt dat men de toString methode kan oproepen in de subklassen van superklasse Activity.
	 */
	public abstract String toString();
	public abstract String getType(); 
	

	
	
}
