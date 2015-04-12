/**
 * 
 */
package com.tracegerm.tracegermws.dto;

import com.tracegerm.tracegermws.model.user.User;

import java.sql.Timestamp;

/**
 * @author askos
 *
 */
public class VisitDetailsDTO {
	
	private Long id;
	
	private Timestamp timestamp;

	private User user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
