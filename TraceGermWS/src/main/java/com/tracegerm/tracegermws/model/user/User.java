package com.tracegerm.tracegermws.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")	
public class User {
	
	@Id
	@Column(name = "USERNAME")
	protected String username;
	
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}	