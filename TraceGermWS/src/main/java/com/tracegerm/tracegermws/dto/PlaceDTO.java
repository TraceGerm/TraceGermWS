/**
 * 
 */
package com.tracegerm.tracegermws.dto;

import java.text.DecimalFormat;



/**
 * @author askos
 *
 */
public class PlaceDTO {
	
	private Long id;
	private double latitude;
	private double longitude;
	private float accuracy;
	
	DecimalFormat newFormat = new DecimalFormat("##.#####");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = Double.valueOf(newFormat.format(latitude));
		
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = Double.valueOf(newFormat.format(longitude));
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	
	

}
