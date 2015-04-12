package com.tracegerm.tracegermws.model.alert;

import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by askos on 10/4/2015.
 */

@Entity
@Table (name = "ALERTS")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ALERT_ID")
    protected Long id;

    @OneToOne(targetEntity = User.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name = "FK_USER")
    private User user;

    @OneToOne(targetEntity = Place.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name ="FK_PLACE")
    private Place place;

    @OneToMany(targetEntity = VisitDetails.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name ="FK_ALERT")
    private List<VisitDetails> detailsList = new ArrayList<>();

    @Column (name = "TIMESTAMP")
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

    public List<VisitDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<VisitDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
