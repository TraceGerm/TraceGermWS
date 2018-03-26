package com.tracegerm.tracegermws.repository;


import com.tracegerm.tracegermws.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findAlertsByTimestamp(Timestamp timestamp);

    List<Alert> findAlertsByAccepted(boolean accepted);

}

