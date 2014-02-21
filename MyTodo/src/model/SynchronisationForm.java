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
import com.google.gdata.data.extensions.Where;
import com.google.gdata.util.common.base.Pair;
import com.util.HibernateUtil;

import jdbc.Synchro;
import jdbc.SynchroId;
import jdbc.Todo;
import jdbc.Utilisateurs;

public class SynchronisationForm {

	public final static String CALENDAR_PARAM =  "exampleCo-exampleApp-1.0";
	private static final String CHAMP_CALENDAR = "calendar";
	private Map<Integer, Pair<String,String>> calendar      = new HashMap<Integer, Pair<String,String>>();

	public void synchro( HttpServletRequest request, Utilisateurs utilisateurs )
	{		
		try {
			generateCalendar(utilisateurs);
			insertionCalendar(utilisateurs, Integer.parseInt(getValeurChamp(request, CHAMP_CALENDAR)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CalendarEventEntry editEvent(Todo t)
	{
		CalendarEventEntry myEvent = new CalendarEventEntry();
		
		myEvent.setTitle(new PlainTextConstruct(t.getTitre()));
		myEvent.setContent(new PlainTextConstruct(t.getDescription()));

		DateTime startTime = new DateTime(t.getEcheance());
		DateTime endTime = new DateTime(t.getEcheance());

		When eventTimes = new When();
		eventTimes.setStartTime(startTime);
		eventTimes.setEndTime(endTime);
		myEvent.addTime(eventTimes);

		Where where = new Where();
		where.setValueString(t.getContexte());

		myEvent.addLocation(where);
		
		return myEvent;
	}
	
	@SuppressWarnings("unchecked")
	private void insertionCalendar(Utilisateurs utilisateur, int idCalendar) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession(); 
		session.beginTransaction(); 

		List<Todo> ListerTodo;
		ListerTodo= (List<Todo>)session.createQuery("select t from Todo t where t.fkIdUtilisateur ='"+ utilisateur.getIdUtilisateur() +"'").list();

		CalendarService myService = new CalendarService(CALENDAR_PARAM);
		myService.setUserCredentials(utilisateur.getGmail(), utilisateur.getGmotDePasse());	

		URL postURL = new URL("http://www.google.com/calendar/feeds/" + 
				calendar.get(idCalendar).getFirst() + "/private/full");		

		CalendarEventFeed myFeed = myService.getFeed(postURL, CalendarEventFeed.class);

		for(Todo t : ListerTodo)
		{

			List<Synchro> ListerSynchro = (List<Synchro>)session.createQuery(
					"select s from Synchro s where s.id.fkIdTodo ='"+ t.getIdTodo() +"'"
							+ "AND s.id.idCalendar = '" + idCalendar + "' ").list();

			CalendarEventEntry myEvent = editEvent(t);	

			boolean exist = false;
			CalendarEventEntry updateEntry = null;
			for(int i=0; i<myFeed.getEntries().size(); i++)
			{
				for(Synchro s : ListerSynchro)
				{
					CalendarEventEntry matchEntry = (CalendarEventEntry)
							myFeed.getEntries().get(i);
					if(matchEntry.getId().equals(s.getId().getFkIdEvent()))
					{
						exist = true;
						updateEntry = matchEntry;
					}
				}
			}		
			if(!exist)
			{
				CalendarEventEntry lastMatchEntry = myService.insert(postURL, myEvent);	
				SynchroId syncId = new SynchroId(t.getIdTodo(), lastMatchEntry.getId() ,idCalendar );
				
				Synchro control = (Synchro)session.createQuery(
						"select s from Synchro s where s.id.idCalendar ='"+ idCalendar +"'"
								+ "AND s.id.fkIdTodo = '"+ t.getIdTodo() +"'").uniqueResult();

				if(control == null)
				{
					Synchro sync = new Synchro( syncId);				
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.save(sync);
					session.getTransaction().commit();
				}
				else
				{
					control.setId(syncId);
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.update(control);
					session.getTransaction().commit();	
				}
			}
			else
			{
				//modification
				if(t.getState() == 1)
				{
					t.setState(0);
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					session.update(t);
					session.getTransaction().commit();	
					//myService.update(postURL, updateEntry);
				}
				// Synchronisation des modification et suppression des TODO.

			}
		}

	}

	public void generateCalendar(Utilisateurs utilisateur) throws Exception 
	{

		CalendarService myService = new CalendarService(CALENDAR_PARAM);
		myService.setUserCredentials(utilisateur.getGmail(), utilisateur.getGmotDePasse());	

		URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
		CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);

		for (int i = 0; i<resultFeed.getEntries().size(); i++) {
			CalendarEntry entry = resultFeed.getEntries().get(i);

			String[] splitArray = null; 
			splitArray = entry.getId().split("/");
			setCalendar(i,new Pair<String, String>(splitArray[splitArray.length-1],entry.getTitle().getPlainText()));
		}
	}

	private void setCalendar( Integer champ, Pair<String,String> message ) {
		calendar.put( champ, message );
	}

	public Map<Integer, Pair<String,String>> getCalendar() {
		return calendar;
	}

	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}
}
