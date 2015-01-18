package com.tracegerm.tracegermws.model.place;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

@Entity
@Table(name = "PLACES")
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLACE_ID")
	protected Long id;

	@Column(name = "LATITUDE")
	protected float latitude;
	
	@Column(name = "LONGITUDE")
	protected float longitude;
	
	@Column(name = "ACCURACY")
	protected float accuracy;
	
	@OneToMany(targetEntity = VisitDetails.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name ="FK_PLACE")
	private List<VisitDetails> detailsList = new ArrayList<VisitDetails>();

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<VisitDetails> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<VisitDetails> detailsList) {
		this.detailsList = detailsList;
	}
	
	
}