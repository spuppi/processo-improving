package com.spuppi.apirestdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="TB_EVENTS")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private long issue;
	
	@NotNull
	private String action;
	
	@NotNull
	private String title;
	
	@NotNull
	private String login;
	
	@NotNull
	private String created_at;
	
	@NotNull
	private String updated_at;
	
	private String closed_at;
	
	public Event() {};
	
	public Event(long issue, String action, String title, String login, String created_at, String updated_at,
			String closed_at) {
		this.issue = issue;
		this.action = action;
		this.title = title;
		this.login = login;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.closed_at = closed_at;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getClosed_at() {
		return closed_at;
	}
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", issue=" + issue + ", action=" + action + ", title=" + title + ", login=" + login
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", closed_at=" + closed_at + "]";
	}	
}
