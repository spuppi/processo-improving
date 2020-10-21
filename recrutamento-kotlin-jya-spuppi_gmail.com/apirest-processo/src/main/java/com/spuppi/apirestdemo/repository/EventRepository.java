package com.spuppi.apirestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spuppi.apirestdemo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	@Query(value = "SELECT * FROM TB_EVENT WHERE issue = :issue", nativeQuery = true)
	Iterable<Event> findEventsByIssue(@Param("issue") long issue);
}