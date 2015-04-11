package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;

/**
 * Created by askos on 10/4/2015.
 */
public interface IAlertService {

    Long createAlert(String username, Long alertId, AlertDTO alertDTO);

    AlertDTO fetchPlaceByID(long alertId) throws ResourceNotFoundException;
}
