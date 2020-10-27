package com.spuppi.apirestdemo.repository;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.spuppi.apirestdemo.model.EventObject;

public interface EventObjectRepository  extends MongoRepository<EventObject, String>{
	
	@Query("{'content.issue.number' : ?0}")
	Page<JSONObject> findEventsDocsByLogin(int issue, PageRequest pageRequest);

	@Query("{'content.issue.user.login' : ?0}")
	Page<JSONObject> findEventsDocsByLogin(String login, PageRequest pageRequest);
	
}
