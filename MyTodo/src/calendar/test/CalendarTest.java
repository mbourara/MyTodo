package calendar.test;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class CalendarTest {

	public static void main(String[] args) throws IOException, ServiceException {
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
		myService.setUserCredentials("myTodoDev@gmail.com", "mytododev33");

		URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);

		System.out.println("Your calendars:");
		System.out.println();

		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			CalendarEntry entry = resultFeed.getEntries().get(i);
			System.out.println("\t" + entry.getTitle().getPlainText());
		}


		URL postURL = new URL("http://www.google.com/calendar/feeds/mytododev@gmail.com/private/full");
		CalendarEventEntry myEvent = new CalendarEventEntry();
		
		//Set the title and description
		myEvent.setTitle(new PlainTextConstruct("YOLOOOOOOOOOOOO Party"));
		myEvent.setContent(new PlainTextConstruct("I am throwing a YOOOOOOLO!"));

		//Create DateTime events and create a When object to hold them, then add
		//the When event to the event
		DateTime startTime = DateTime.parseDateTime("2014-02-17T17:00:00-08:00");
		DateTime endTime = DateTime.parseDateTime("2014-02-18T17:00:00-08:00");
		When eventTimes = new When();
		eventTimes.setStartTime(startTime);
		eventTimes.setEndTime(endTime);
		myEvent.addTime(eventTimes);

		// POST the request and receive the response:
		CalendarEventEntry insertedEntry = myService.insert(postURL, myEvent);
	}
}