package com.tracegerm.tracegermws.dto;

import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by askos on 10/4/2015.
 */
public class AlertDTO {

    private Long id;
    private User user;
    private Place place;
    private List<VisitDetails> detailsList = new ArrayList<VisitDetails>();
    private Timestamp timestamp;

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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<VisitDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<VisitDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
