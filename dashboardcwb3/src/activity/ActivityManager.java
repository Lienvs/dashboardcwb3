package activity;
//De benodigde java-elementen importeren
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//De benodigde Google App Engine services importeren
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
//De benodigde eigen klassen importeren
import user.UserManager;
import course.CourseManager;

public class ActivityManager {
	
	//Er wordt een variabele "instance" van deze eigenste klasse aangemaakt en op null geïnitialiseerd.
	private static ActivityManager instance = null;	
	
	/**
	*Methode om een instantie te nemen van ActivityManager. Deze klasse is een singleton, 
	*dus als er al een instantie bestaat van deze klasse, geeft de methode de bestaande instantie terug.
	*
	*@author cwb3
	*@param /
	*@return de instantie van de ActivityManager die al geïnitaliseerd was of die wordt aangemaakt indien er nog geen ActivityManager bestond.
	*/
	public static ActivityManager getInstance() {
		if( instance == null ) {
			instance = new ActivityManager();
		}
		return instance;
	}
	
	/**
	 * Constructor
	 */
	public ActivityManager() {			
	}	
	
	/**
	 * Methode die er voor zorgt dat er een Activity in de database opgeslagen wordt.
	 * 
	 * @author cwb3
	 * @param activity de activiteit die in de database opgeslagen dient te worden
	 */
	public void addActivity(Activity activity){
		//De datastore wordt aangeroepen
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Er wordt een nieuwe entiteit "Activity" aangemaakt
		Entity Activity = new Entity("Activity",activity.getStart().toString());
		//De eigenschap "startdatum" wordt aan de aangemaakte entity meegegeven
		Activity.setProperty("startdatum",activity.getStart());
		//De eigenschap "activitytype" wordt aan de entity meegegeven
		Activity.setProperty("activitytype", activity.getActivityType());
		//De eigenschap "type" wordt aan de entity meegegeven
		Activity.setProperty("type", activity.getType());
		if (activity.getActivityType().equals("scolair")){
			//Als de activiteit een scolaire activiteit is, zet de eigenschap "course" op de naam van de course
			Activity.setProperty("course", CourseManager.getInstance().getKey(activity.getCourse().toString()));
		}
		//Dit alles (entity + eigenschappen) wordt in de database opgeslagen
		datastore.put(Activity);
	}
	
	/**
	 * Methode die ervoor zorgt dat een Activity in de database geüpdatet kan worden.
	 * (=nieuwe eigenschappen aan de entity toekennen als er variabelen van waarde veranderen)
	 * 
	 * @author cwb3
	 * @param rating de rating die de user aan de activity geeft
	 * @param comment de comment die de user aan de activity geeft
	 * @param place de plaats waar de activity plaatsvond
	 * @param stype het studietype (les/oefenzitting/zelfstudie) van de activity
	 * @param buddy de eventuele studdybuddy tijdens de zelfstudie
	 */
	public void updateActivity(int rating, String comment, String place, String stype, String buddy){
		//Neem een instantie van de Calendar-klasse
		Calendar cal = Calendar.getInstance();
		//De datastoreservice wordt aangeroepen
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();		
		//Een nieuwe Arraylist met Keys wordt aangemaakt
		ArrayList<Key> keys = new ArrayList<Key>();
		//De Arraylist wordt opgevuld met de keys van alle Activities van de user
		keys = UserManager.getInstance().getActivityKeys();
		//Itereer over alle keys in de ArrayList
		for(Key l : keys){
			//Aanmaken van de variabele txn, die we kunnen gebruiken telkens er een transactie mbt de database plaatsvindt
			Transaction txn = datastore.beginTransaction();
			//Probeer het volgende:
			try {		
				//Vraag de gewenste activity op uit de database mbv diens key (over elke Activity wordt geïtereerd)
				Entity Activity = datastore.get(l);
				//Als de opgevraagde Activity al een stopdatum heeft, gebeurt er niets
				if (Activity.hasProperty("stopdatum")){					
				}
				//Als de Activity nog geen stopdatum heeft, gebeurt het volgende:
				else {
					//De stopdatum wordt ingesteld op de huidige tijd
					Activity.setProperty("stopdatum", cal.getTime());
					//De rating die de gebruiker ingegeven heeft wordt als eigenschap aan de entity Activity toegewezen
					Activity.setProperty("rating", rating);
					//De comment die de gebruiker ingegeven heeft wordt als eigenschap aan de entity Activity toegewezen
					Activity.setProperty("comment", comment);
					//Als het type van de Activity "Zelfstudie" is, gebeurt het volgende:
					if (((String)Activity.getProperty("type")).equals("Zelfstudie")){
						//De eigenschap "studielocatie" (die de gebruiker ingegeven heeft) wordt aan de entity Activity toegewezen
						Activity.setProperty("studielocatie", place);
						//De eigenschap "studietype" (die de gebruiker ingegeven heeft) wordt aan de entity Activity toegewezen
						Activity.setProperty("studietype", stype);
						//De eigenschap "buddy" (die de gebruiker ingegeven heeft) wordt aan de entity Activity toegewezen
						Activity.setProperty("buddy", buddy);
					}
					//De Activity wordt weer in de database opgeslagen
					datastore.put(Activity);
				}
			//Geef aan dat de huidige transactie afgelopen is		    
		    txn.commit();
		} 
		//Als de foutmelding gegeven wordt dat de gewenste Activity niet in de database gevonden werd, gebeurt het volgende:
		catch (EntityNotFoundException e){
			//Als de transactie bezig is:
			if (txn.isActive()) {
				//Undo alles wat gebeurd is binnen deze transactie, en slaag deze over
		        txn.rollback();
		    }
		    
		}
		}
		}
	
	/**
	 * Deze methode geeft alle activiteiten van alle users terug in een lijst
	 * 
	 * @author cwb3
	 * @return activities een ArrayList met alle activiteiten van alle users
	 */
	public ArrayList<Activity> getAllActivities(){
		//Maak de ArrayList activities aan, hierin zullen alle activities in de database gestoken worden
		ArrayList<Activity> activities = new ArrayList<Activity>();
		//De datastoreservice wordt aangeroepen
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Er wordt een query aangemaakt die op de entity "Activity" zal werken
		Query q = new Query("Activity");
		//De aangemaakte query is gereed voor gebruik
		PreparedQuery pq = datastore.prepare(q);
		//De query itereert over alle Activities (de activity die momenteel behandeld wordt, wordt "result" genoemd
		for (Entity result : pq.asIterable()) {
			//Maak een nieuwe variabele "type" aan, met als waarde het type van de activiteit
			String type = (String) result.getProperty("type");
			//Maak een nieuwe variabele "activitytype" aan, met als waarde het activitytype van de activiteit
			String activityType = (String) result.getProperty("activitytype");
			//Als het type activiteit een les (hoorcollege) is, gebeurt het volgende:
			if(type.equals("les")){
				//De variabele "les" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gegeven werd
				Lecture les = new Lecture(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				//Stel de startdatum van les in op de startdatum die uit de database gehaald wordt
				les.setStart((Date)result.getProperty("startdatum"));
				//Als de les een stopdatum heeft, gebeurt het volgende:
				if (result.hasProperty("stopdatum")){
					//Stel de stopdatum van les in op de stopdatum die uit de database gehaald wordt
					les.setStop((Date)result.getProperty("stopdatum"));
				}
				//Als de les een rating heeft, gebeurt het volgende:
				if (result.hasProperty("rating")){
					//Stel de rating van les in op de rating die uit de database gehaald wordt
					les.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				//Als de les een comment heeft, gebeurt het volgende:
				if (result.hasProperty("comment")){
					//Stel de comment van les in op de comment die uit de database gehaald wordt
					les.postComment((String)result.getProperty("comment"));
				}
				//Voeg "les" toe aan de ArrayList activities
				activities.add(les);
			}
			//Als het type activiteit een oefenzitting is, gebeurt het volgende:
			else if(type.equals("Oefenzitting")){
				//De variabele "oefenzitting" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gegeven werd
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				//Stel de startdatum van oefenzitting in op de startdatum die uit de database gehaald wordt
				oefenzitting.setStart((Date)result.getProperty("startdatum"));
				//Als de oefenzitting een stopdatum heeft, gebeurt het volgende:
				if (result.hasProperty("stopdatum")){
					//Stel de stopdatum van oefenzitting in op de stopdatum die uit de database gehaald wordt
					oefenzitting.setStop((Date)result.getProperty("stopdatum"));
				}
				//Als de oefenzitting een rating heeft, gebeurt het volgende:
				if (result.hasProperty("rating")){
					//Stel de rating van oefenzitting in op de rating die uit de database gehaald wordt
					oefenzitting.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				//Als de oefenzitting een comment heeft, gebeurt het volgende:
				if (result.hasProperty("comment")){
					//Stel de comment van oefenzitting in op de comment die uit de database gehaald wordt
					oefenzitting.postComment((String)result.getProperty("comment"));
				}
				//Voeg "oefenzitting" toe aan de ArrayList activities
				activities.add(oefenzitting);
			}
			//Als het type activiteit zelfstudie is, gebeurt het volgende:
			else if(type.equals("Zelfstudie")){
				//De variabele "zelfstudie" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gestudeerd werd
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) result.getProperty("course")));
				//Stel de startdatum van zelfstudie in op de startdatum die uit de database gehaald wordt
				zelfstudie.setStart((Date)result.getProperty("startdatum"));
				//Als de zelfstudie een stopdatum heeft, gebeurt het volgende:
				if (result.hasProperty("stopdatum")){
					//Stel de stopdatum van zelfstudie in op de stopdatum die uit de database gehaald wordt
					zelfstudie.setStop((Date)result.getProperty("stopdatum"));
				}
				//Als de zelfstudie een rating heeft, gebeurt het volgende:
				if (result.hasProperty("rating")){
					//Stel de rating van zelfstudie in op de rating die uit de database gehaald wordt
					zelfstudie.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				//Als de zelfstudie een comment heeft, gebeurt het volgende:
				if (result.hasProperty("comment")){
					//Stel de comment van oefenzitting in op de comment die uit de database gehaald wordt
					zelfstudie.postComment((String)result.getProperty("comment"));
				}
				//Als de zelfstudie een buddy heeft, gebeurt het volgende:
				if (result.hasProperty("buddy")){
					//Stel de buddy van zelfstudie in op de buddy die uit de database gehaald wordt
					zelfstudie.setStudyBuddy((String)result.getProperty("buddy"));
				}	
				//Als de zelfstudie een studielocatie heeft, gebeurt het volgende:
				if (result.hasProperty("studielocatie")){
					//Stel de studielocatie van zelfstudie in op de studielocatie die uit de database gehaald wordt
					zelfstudie.setLocation(StudyLocation.getStudyLocation((String)result.getProperty("studielocatie")));
				}
				//Als de zelfstudie een studietype heeft, gebeurt het volgende:
				if (result.hasProperty("studietype")){
					//Stel het studietype van zelfstudie in op het studietype dat uit de database gehaald wordt
					zelfstudie.setType(StudyType.getStudyType((String)result.getProperty("studietype")));
				}
				//Voeg "zelfstudie" toe aan de ArrayList activities
				activities.add(zelfstudie);
			}
			//Als het type activiteit "fun" is, gebeurt het volgende:
			else if(activityType.equals("fun")){
				//De variabele "ex" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het soort fun-activiteit dat gedaan werd
				ExtraFun ex = ExtraFun.getExtraFun((String) result.getProperty("type"));
				//De variabele "act" wordt aangemaakt. Dit is een ExtraCurricularActivity die "ex" meekrijgt als parameter
				ExtraCurricularActivity act = new ExtraCurricularActivity(ex);
				//Stel de startdatum van act in op de startdatum die uit de database gehaald word
				act.setStart((Date)result.getProperty("startdatum"));
				//Als de fun-activiteit een stopdatum heeft, gebeurt het volgende:
				if (result.hasProperty("stopdatum")){
					//Stel de stopdatum van act in op de stopdatum die uit de database gehaald wordt
					act.setStop((Date)result.getProperty("stopdatum"));
				}
				//Als de fun-activiteti een rating heeft, gebeurt het volgende:
				if (result.hasProperty("rating")){
					//Stel de rating van act in op de rating die uit de database gehaald wordt
					act.setRating(Integer.parseInt(result.getProperty("rating").toString()));
				}
				//Als de fun-activiteti een comment heeft, gebeurt het volgende:
				if (result.hasProperty("comment")){
					//Stel de comment van act in op de comment die uit de database gehaald wordt
					act.postComment((String)result.getProperty("comment"));
				}
				//Voeg "act" toe aan de ArrayList activities
				activities.add(act);
			}			
		}
		//Return de gevulde ArrayList met alle activities
		return activities;
	}	
	
	/**
	 * Methode om mbv een key een welbepaalde activiteit uit de database op te vragen
	 * 
	 * @author cwb3
	 * @param k de key van de gewenste activity
	 * @return act de opgevraagde activity
	 */
	public Activity getActivity(Key k){
		//De datastoreservice wordt aangeroepen
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Maak een nieuwe variabele "act" aan en stel de waarde hiervan in op null
		Activity act = null;
		//Probeer het volgende:
		try{
			//Vraag de Activity op uit de database die als key de opgegeven key (k) heeft
			Entity Activity = datastore.get(k);
			//Maak de variabele "type" aan en stel zijn waarde in op het type van de uit de database gehaalde Activity
			String type = (String) Activity.getProperty("type");
			//Maak de variabele "activitytype" aan en stel zijn waarde in op het activitytype van de uit de database gehaalde Activity
			String activityType = (String) Activity.getProperty("activitytype");
			//Als het type activiteit een les (hoorcollege) is, gebeurt het volgende:
			if(type.equals("les")){
				//De variabele "les" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gegeven werd
				Lecture les = new Lecture(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				//Stel de startdatum van les in op de startdatum die uit de database gehaald wordt
				les.setStart((Date)Activity.getProperty("startdatum"));
				//Als de les een stopdatum heeft, gebeurt het volgende:
				if (Activity.hasProperty("stopdatum")){
					//Stel de stopdatum van les in op de stopdatum die uit de database gehaald wordt
					les.setStop((Date)Activity.getProperty("stopdatum"));
				}
				//Als de les een rating heeft, gebeurt het volgende:
				if (Activity.hasProperty("rating")){
					//Stel de rating van les in op de rating die uit de database gehaald wordt
					les.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				//Als de les een comment heeft, gebeurt het volgende:
				if (Activity.hasProperty("comment")){
					//Stel de comment van les in op de rating die uit de database gehaald wordt
					les.postComment((String)Activity.getProperty("comment"));
				}
				//Stel de waarde van de variabele "act" in op "les" ("act" is bijgevolg volledig identiek aan "les")
				act=les;
			}
			//Als het type activiteit een oefenzitting is, gebeurt het volgende:
			else if(type.equals("Oefenzitting")){
				//De variabele "oefenzitting" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gegeven werd
				Practice oefenzitting = new Practice(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				//Stel de startdatum van oefenzitting in op de startdatum die uit de database gehaald word
				oefenzitting.setStart((Date)Activity.getProperty("startdatum"));
				//Als de oefenzitting een stopdatum heeft, gebeurt het volgende:
				if (Activity.hasProperty("stopdatum")){
					//Stel de stopdatum van oefenzitting in op de stopdatum die uit de database gehaald wordt
					oefenzitting.setStop((Date)Activity.getProperty("stopdatum"));
				}
				//Als de oefenzitting een rating heeft, gebeurt het volgende:
				if (Activity.hasProperty("rating")){
					//Stel de rating van oefenzitting in op de rating die uit de database gehaald wordt
					oefenzitting.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				//Als de oefenzitting een comment heeft, gebeurt het volgende:
				if (Activity.hasProperty("comment")){
					//Stel de comment van oefenzitting in op de comment die uit de database gehaald wordt
					oefenzitting.postComment((String)Activity.getProperty("comment"));
				}
				//Stel de waarde van de variabele "act" in op "oefenzitting" ("act" is bijgevolg volledig identiek aan "oefenzitting")
				act=oefenzitting;
			}
			//Als het type activiteit zelfstudie is, gebeurt het volgende:
			else if(type.equals("Zelfstudie")){
				//De variabele "zelfstudie" wordt aangemaakt en zijn waarde wordt ingesteld op de naam van het vak dat gestudeerd werd
				IndividualStudy zelfstudie = new IndividualStudy(CourseManager.getInstance().getCourse((Key) Activity.getProperty("course")));
				//Stel de startdatum van zelfstudie in op de startdatum die uit de database gehaald wordt
				zelfstudie.setStart((Date)Activity.getProperty("startdatum"));
				//Als de zelfstudie een stopdatum heeft, gebeurt het volgende:
				if (Activity.hasProperty("stopdatum")){
					//Stel de stopdatum van zelfstudie in op de stopdatum die uit de database gehaald wordt
					zelfstudie.setStop((Date)Activity.getProperty("stopdatum"));
				}
				//Als de zelfstudie een rating heeft, gebeurt het volgende:
				if (Activity.hasProperty("rating")){
					//Stel de rating van zelfstudie in op de rating die uit de database gehaald wordt
					zelfstudie.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				//Als de zelfstudie een comment heeft, gebeurt het volgende:
				if (Activity.hasProperty("comment")){
					//Stel de comment van zelfstudie in op de comment die uit de database gehaald wordt
					zelfstudie.postComment((String)Activity.getProperty("comment"));
				}
				//Als de zelfstudie een studybuddy heeft, gebeurt het volgende:
				if (Activity.hasProperty("buddy")){
					//Stel de studybuddy van zelfstudie in op de studybuddy die uit de database gehaald wordt
					zelfstudie.setStudyBuddy((String)Activity.getProperty("buddy"));
				}	
				//Als de zelfstudie een studielocatie heeft, gebeurt het volgende:
				if (Activity.hasProperty("studielocatie")){
					//Stel de locatie van zelfstudie in op de locatie die uit de database gehaald wordt
					zelfstudie.setLocation(StudyLocation.getStudyLocation((String)Activity.getProperty("studielocatie")));
				}
				//Als de zelfstudie een studietype heeft, gebeurt het volgende:
				if (Activity.hasProperty("studietype")){
					//Stel het type van zelfstudie in op het type die uit de database gehaald wordt
					zelfstudie.setType(StudyType.getStudyType((String)Activity.getProperty("studietype")));
				}
				//Stel de waarde van de variabele "act" in op "zelfstudie" ("act" is bijgevolg volledig identiek aan "zelfstudie")
				act=zelfstudie;
			}
			//Als het type activiteit "fun" is, gebeurt het volgende:
			else if(activityType.equals("fun")){
				//Als het type activiteit fun is, gebeurt het volgende:
				ExtraFun ex = ExtraFun.getExtraFun((String) Activity.getProperty("type"));
				//De variabele "actt" wordt aangemaakt. Dit is een ExtraCurricularActivity die "ex" meekrijgt als parameter
				ExtraCurricularActivity actt = new ExtraCurricularActivity(ex);
				//Stel de startdatum van actt in op de startdatum die uit de database gehaald word
				actt.setStart((Date)Activity.getProperty("startdatum"));
				//Als de fun-activiteit een stopdatum heeft, gebeurt het volgende:
				if (Activity.hasProperty("stopdatum")){
					//Stel de stopdatum van actt in op de stopdatum die uit de database gehaald wordt
					actt.setStop((Date)Activity.getProperty("stopdatum"));
				}
				//Als de fun-activiteit een rating heeft, gebeurt het volgende:
				if (Activity.hasProperty("rating")){
					//Stel de rating van actt in op de rating die uit de database gehaald wordt
					actt.setRating(Integer.parseInt(Activity.getProperty("rating").toString()));
				}
				//Als de fun-activiteit een comment heeft, gebeurt het volgende:
				if (Activity.hasProperty("comment")){
					//Stel de comment van actt in op de comment die uit de database gehaald wordt
					actt.postComment((String)Activity.getProperty("comment"));
				}
				//Stel de waarde van de variabele "act" in op "actt" ("act" is bijgevolg volledig identiek aan "actt")
				act=actt;
			}									
		}
		//Als er geen entity bestaat met opgegeven key, doe niets
		catch (EntityNotFoundException e){
		}
		//Return de opgevraagde activity
		return act;
	}
}
