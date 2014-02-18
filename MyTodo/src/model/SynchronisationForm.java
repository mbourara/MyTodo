package model;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.util.HibernateUtil;

import jdbc.Todo;
import jdbc.Utilisateurs;

public class SynchronisationForm {

	public final static String CALENDAR_PARAM =  "exampleCo-exampleApp-1.0";
	public final static String CALENDAR_KEY = "mytodo";
	
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

		for(Todo t : ListerTodo)
		{
			CalendarEventEntry myEvent = new CalendarEventEntry();
			//Set the title and description
			myEvent.setTitle(new PlainTextConstruct(t.getTitre()));
			myEvent.setContent(new PlainTextConstruct(t.getDescription()));
			myEvent.setId(CALENDAR_KEY+t.getIdTodo());

			System.out.println(t.getEcheanceBegin());
			System.out.println(t.getEcheanceEnd());
			
			DateTime startTime = new DateTime(t.getEcheanceBegin());
			DateTime endTime = new DateTime(t.getEcheanceEnd());
			//DateTime startTime = DateTime.parseDateTime("2014-02-17T17:00:00-08:00");
			//DateTime endTime = DateTime.parseDateTime("2014-02-18T17:00:00-08:00");
			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEvent.addTime(eventTimes);

			myService.insert(postURL, myEvent);
		}

	}
}
