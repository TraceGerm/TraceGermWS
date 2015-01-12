/**
 * 
 */
package com.tracegerm.tracegermws.dto;

import java.sql.Timestamp;

/**
 * @author askos
 *
 */
public class VisitDetailsDTO {
	
	private Long id;
	
	private Timestamp timeStamp;
	
	//private User user;

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
