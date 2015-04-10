package com.tracegerm.tracegermws.model.place;

import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLACES")
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLACE_ID")
	protected Long id;

	@Column(name = "LATITUDE", precision=2, scale=4)
	protected double latitude;
	
	@Column(name = "LONGITUDE", precision=2, scale=4)
	protected double longitude;
	
	@Column(name = "ACCURACY")
	protected float accuracy;


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {

		this.longitude = longitude;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}