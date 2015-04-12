package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.alert.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by askos on 10/4/2015.
 */
public interface IAlertRepository extends JpaRepository<Alert, Long> {

}

