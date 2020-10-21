package com.spuppi.apirestdemo.repository;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spuppi.apirestdemo.model.EventObject;

public interface EventObjectRepository  extends MongoRepository<EventObject, String>{
	
	//db.getCollection('EVENTS').find({"content.issue.user.login":"spuppi"})
	
	@Query("{'content.issue.user.login' : ?0}")
    Iterable<JSONObject> findByEventsObjectsByLogin(String login);

//	@Query("{'content.issue.user.login' : ?0}")
//	Iterable<JSONObject> findByEventsObjectsByIssue(Long issue);
}
