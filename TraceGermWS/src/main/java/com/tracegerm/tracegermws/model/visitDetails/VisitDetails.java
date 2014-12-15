package com.tracegerm.tracegermws.model.visitDetails;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "VISIT_DETAILS")
public class VisitDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "VISIT_DETAILS_ID")
	protected Long id;
	
	@Column(name = "TIMESTAMP")
	protected Timestamp timeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}