package com.spuppi.apirestdemo.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.spuppi.apirestdemo.model.Event;
import com.spuppi.apirestdemo.resource.EventResource;

public class EventService {

	private static final Logger log = LogManager.getLogger(EventResource.class);

	public Event mapEvent(Object obj) {
		
		log.info("mapEvent: " + obj);

		Event event = null;
		
		try {

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) obj;

			Object parseIssue;

			parseIssue = parser.parse(jsonObject.get("issue").toString());

			JSONObject jsonIssue = (JSONObject) parseIssue;
		
			event = new Event(Long.parseLong(String.valueOf(
					jsonIssue.get("number"))), 
					String.valueOf(jsonObject.get("action")),
					String.valueOf(jsonIssue.get("title")), 
					String.valueOf(((JSONObject) jsonIssue.get("user")).get("login")),
					String.valueOf(jsonIssue.get("created_at")), 
					String.valueOf(jsonIssue.get("updated_at")), 
					String.valueOf(jsonIssue.get("closed_at")) 
					);

			return event;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return event;
	}

}
