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
	void listEventsByIssue() {
		
		Long issue = 1l;
		
		Iterable<JSONObject> events = eventObjectRepository.findEventsByIssue(issue);
		
		for(JSONObject event : events) {
			System.out.println(event);
		}	
	}
	
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
			
			EventObject eventObj = new EventObject(event.getIssue(), String.valueOf(jsonObject));
			
			if(event != null) {
				eventRepository.save(event);
				eventObjectRepository.save(eventObj);
			}

			System.out.println(event);

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

}
