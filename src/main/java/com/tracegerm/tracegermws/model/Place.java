package com.tracegerm.tracegermws.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tracegerm.tracegermws.model.serializer.PositionDeserializer;
import com.tracegerm.tracegermws.model.serializer.PositionSerializer;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "PLACES")
public class Place implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLACE_ID")
	private Long id;

	@Column(name = "POSITION", columnDefinition = "Geometry")
	@JsonSerialize(using = PositionSerializer.class)
	@JsonDeserialize(using = PositionDeserializer.class)
	private Point position;

	@Column(name = "ACCURACY")
	private float accuracy;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "FK_USER", nullable = false, updatable = false)
	private User user;

	@Column(name = "TIMESTAMP")
	private Timestamp timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
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
}