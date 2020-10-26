package com.spuppi.apirestdemo.model;

import javax.persistence.Id;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EVENTS")
public class EventObject {
	
	@Id
	private String id;
	private JSONObject content;
	
	public EventObject(JSONObject content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JSONObject getContent() {
		return content;
	}
	public void setContent(JSONObject content) {
		this.content = content;
	}
}