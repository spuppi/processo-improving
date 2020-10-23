package com.spuppi.apirestdemo.resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spuppi.apirestdemo.model.Event;
import com.spuppi.apirestdemo.model.EventObject;
import com.spuppi.apirestdemo.repository.EventObjectRepository;
import com.spuppi.apirestdemo.repository.EventRepository;
import com.spuppi.apirestdemo.service.EventService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/issues")
public class EventResource {

	private static final JSONParser PARSER = new JSONParser();
	
	private static final Logger log = LogManager.getLogger(EventResource.class);

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventObjectRepository eventObjectRepository;
	
	@ApiOperation(value = "Retorna os eventos associados a issue informada")
	@GetMapping(value="/{issue}/events", produces = "application/json")
	public Iterable<Event> listEvents(@PathVariable(value="issue") Long issue){
		Iterable<Event> events = eventRepository.findEventsByIssue(issue);
		return events;
	}
	
	@ApiOperation(value = "Retorna os objetos completos dos eventos associados a issue informada")
	@GetMapping(value="/{issue}/events/documents", produces = "application/json")
	public Iterable<JSONObject> listEventsObjs(@PathVariable(value="issue") Long issue){
		Iterable<JSONObject> events = eventObjectRepository.findEventsByIssue(issue);
		return events;
	}

	@ApiOperation(value = "Cadastrar evento")
	@PostMapping
	public ResponseEntity<Event> addEvent(@RequestBody JSONObject postGit) {
		
		log.info("addEvent: " + postGit);
		
		Event event = null;
				
		try {

			Object obj = PARSER.parse(String.valueOf(postGit));
		
			EventService eventService = new EventService();
			event = eventService.mapEvent(obj);
			
			EventObject eventObj = new EventObject(event.getIssue(), String.valueOf(postGit));
			
			log.info("event mapped: " + event);

			if(event != null) {
				eventRepository.save(event);
				eventObjectRepository.save(eventObj);
			}

			return ResponseEntity.ok(event);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(event);
	}
	
	@ApiOperation(value = "Retorna os eventos associados ao usuário informada")
	@GetMapping(value="/events/login/{login}", produces = "application/json")
	public Iterable<Object> findEventsLogin(@PathVariable(value="login") String login) {
		
		//O ideal seria utilizar para isso um banco de documentos ex. mongodb
		
		String parameter = "\"login\":\"" + login + "\"";
		
		Iterable<Object> events = eventObjectRepository.findEventsByLogin(parameter);
		return events;
	}
}
