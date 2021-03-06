package com.spuppi.apirestdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spuppi.apirestdemo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	@Query(value = "SELECT * FROM TB_EVENTS WHERE issue = :issue", nativeQuery = true)
	Page<Event> findEventsByIssue(@Param("issue") long issue, Pageable pageable);

}