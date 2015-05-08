package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.alert.Alert;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by askos on 10/4/2015.
 */
public interface IAlertService {

    Long createAlert(String username, Long placeId, AlertDTO alertDTO);

    AlertDTO fetchAlertByID(long alertId) throws ResourceNotFoundException;

    List<AlertDTO> fetchAlertsByTimestamp (Timestamp timestamp) throws ResourceNotFoundException ;

    List<AlertDTO> fetchAllAlerts() throws ResourceNotFoundException ;
}
