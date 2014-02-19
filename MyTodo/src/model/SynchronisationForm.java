package model;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.util.HibernateUtil;

import jdbc.Todo;
import jdbc.Utilisateurs;

public class SynchronisationForm {

	public final static String CALENDAR_PARAM =  "exampleCo-exampleApp-1.0";
	private Map<Integer, String> calendar      = new HashMap<Integer, String>();
	private int nbCalendar	= 0;

	public void synchro( HttpServletRequest request, Utilisateurs utilisateurs )
	{
		try {
			insertionCalendar(utilisateurs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void insertionCalendar(Utilisateurs utilisateur) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 

		List<Todo> ListerTodo;
		ListerTodo= (List<Todo>)session.createQuery("select t from Todo t where t.fkIdUtilisateur ='"+ utilisateur.getIdUtilisateur() +"'").list();



		CalendarService myService = new CalendarService(CALENDAR_PARAM);
		myService.setUserCredentials(utilisateur.getGmail(), utilisateur.getGmotDePasse());	

		URL postURL = new URL("http://www.google.com/calendar/feeds/" + utilisateur.getGmail() + "/private/full");		

		CalendarEventFeed myFeed = myService.getFeed(postURL, CalendarEventFeed.class);

		for(Todo t : ListerTodo)
		{
			CalendarEventEntry myEvent = new CalendarEventEntry();

			myEvent.setTitle(new PlainTextConstruct(t.getTitre()));
			myEvent.setContent(new PlainTextConstruct(t.getDescription()));

			DateTime startTime = new DateTime(t.getEcheanceBegin());
			DateTime endTime = new DateTime(t.getEcheanceEnd());

			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEvent.addTime(eventTimes);


			boolean exist = false;
			for(int i=0; i<myFeed.getEntries().size(); i++)
			{
				CalendarEventEntry matchEntry = (CalendarEventEntry)
						myFeed.getEntries().get(i);
				if(matchEntry.getId().equals(t.getIdCalendar()))
					exist = true;
			}		
			if(!exist)
			{
				CalendarEventEntry lastMatchEntry = myService.insert(postURL, myEvent);		
				String query = "UPDATE Todo SET ID_Calendar = '"+ lastMatchEntry.getId() +"' WHERE ID_Todo = '" + t.getIdTodo() 
						+ "' AND FK_ID_Utilisateur = '"+ utilisateur.getIdUtilisateur() + "'";
				SQLQuery sqlQuery = session.createSQLQuery(query);
				sqlQuery.executeUpdate();

			}
		}

	}

	public void generateCalendar(Utilisateurs utilisateur) throws Exception 
	{

		CalendarService myService = new CalendarService(CALENDAR_PARAM);
		myService.setUserCredentials(utilisateur.getGmail(), utilisateur.getGmotDePasse());	

		// Send the request and print the response
		URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
		CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);

		nbCalendar = 0;
		for (int i = 0; i<resultFeed.getEntries().size(); i++) {
			CalendarEntry entry = resultFeed.getEntries().get(i);
			nbCalendar++;

			setCalendar(i,entry.getTitle().getPlainText());
		}
	}

	private void setCalendar( Integer champ, String message ) {
		calendar.put( champ, message );
	}

	public Map<Integer, String> getCalendar() {
		return calendar;
	}
	
	public int getNbCalendar() {
		return nbCalendar;
	}
}
