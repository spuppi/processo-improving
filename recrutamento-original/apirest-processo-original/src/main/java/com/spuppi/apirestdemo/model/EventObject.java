package com.spuppi.apirestdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="TB_EVENT_OBJ")
public class EventObject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private long issue;
	
	@NotNull
	@Column(columnDefinition="TEXT")
	private String obj;
	
	public EventObject() {};

	public EventObject(long issue, String obj) {
		this.issue = issue;
		this.obj = obj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIssue() {
		return issue;
	}

	public void setIssue(long issue) {
		this.issue = issue;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "EventObject [id=" + id + ", issue=" + issue + ", obj=" + obj + "]";
	}
}
