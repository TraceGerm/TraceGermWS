package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.alert.Alert;
import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by askos on 10/4/2015.
 */
public interface IAlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findAlertsByTimestamp(Timestamp timestamp);

}

