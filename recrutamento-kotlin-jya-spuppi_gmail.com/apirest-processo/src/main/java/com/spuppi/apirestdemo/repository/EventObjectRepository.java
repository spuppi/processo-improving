package com.spuppi.apirestdemo.repository;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spuppi.apirestdemo.model.EventObject;

public interface EventObjectRepository  extends JpaRepository<EventObject, Long>{
	
	@Query(value = "SELECT * FROM TB_EVENT_OBJ WHERE obj LIKE %:login%", nativeQuery = true)
	Iterable<Object> findEventsByLogin(@Param("login") String login);

	@Query(value = "SELECT * FROM TB_EVENT_OBJ WHERE issue = :issue", nativeQuery = true)
	Iterable<JSONObject> findEventsByIssue(@Param("issue") long issue);
}
