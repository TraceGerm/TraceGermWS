package com.tracegerm.tracegermws.model.visitDetails;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.user.User;


@Entity
@Table(name= "VISIT_DETAILS")
public class VisitDetails {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VISIT_DETAILS_ID")
	protected Long id;
	
	@ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "FK_USER", nullable = false, updatable = false)
	private User user;

	@ManyToOne(targetEntity = Place.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "FK_PLACE", nullable = false, updatable = false)
	private Place place;
	

	@Column(name = "TIMESTAMP")
	protected Timestamp timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}