package com.spuppi.apirestdemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spuppi.apirestdemo.model.Event;
import com.spuppi.apirestdemo.model.EventObject;
import com.spuppi.apirestdemo.repository.EventObjectRepository;
import com.spuppi.apirestdemo.repository.EventRepository;
import com.spuppi.apirestdemo.service.EventService;

@SpringBootTest
class ApirestDemoApplicationTests {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventObjectRepository eventObjectRepository;
	
	@Test
	void addEvent() {
	
		Event event = null;
		
		String pathLocalEvent = "./src/test/java/event.json";
		
		try {
			
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(new FileReader(pathLocalEvent));
			JSONObject jsonObject = (JSONObject) obj;
		
			EventService eventService = new EventService();
			event = eventService.mapEvent(obj);
			
			EventObject eventObj = new EventObject(jsonObject);
			
			if(event != null) {
				eventRepository.save(event);
				eventObjectRepository.save(eventObj);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	void listEventsByIssue() {
		
		Long issue = 3l;
		
		Iterable<Event> events = eventRepository.findEventsByIssue(issue);
		
		for(Event event : events) {
			System.out.println(event);
		}	
	}
	
	@Test
    public void findEventsObjsTest(){
		
		JSONParser parser = new JSONParser();
		
		Iterable<EventObject> events = eventObjectRepository.findAll();
        
		for(EventObject event : events){
		
			try {
				Object obj = parser.parse(event.getContent().toJSONString());
				JSONObject jsonObject = (JSONObject) obj;
				
				System.out.println(((JSONObject) ((JSONObject) jsonObject.get("issue")).get("user")).get("login"));
				System.out.println(jsonObject);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
