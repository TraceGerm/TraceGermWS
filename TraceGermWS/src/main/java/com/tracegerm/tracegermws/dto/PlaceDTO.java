/**
 * 
 */
package com.tracegerm.tracegermws.dto;


/**
 * @author askos
 *
 */
public class PlaceDTO {
	
	private Long id;
	private float latitude;
	private float longitude;
	private float accuracy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	
	

}
