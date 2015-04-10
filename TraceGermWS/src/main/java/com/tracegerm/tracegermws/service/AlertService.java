package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.mapper.AlertDTOtoAlertMapper;
import com.tracegerm.tracegermws.mapper.AlertToAlertDTOMapper;
import com.tracegerm.tracegermws.model.alert.Alert;
import com.tracegerm.tracegermws.repository.IAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by askos on 10/4/2015.
 */
@Service
@Transactional
public class AlertService implements IAlertService {

    private final IAlertRepository alertRepository;

    @Autowired
    public AlertService(IAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Long createAlert(AlertDTO alertDTO) {
        Alert alert = new AlertDTOtoAlertMapper().map(alertDTO, new Alert());
        alert = alertRepository.save(alert);

        return alert.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public AlertDTO fetchPlaceByID(long alertId) throws ResourceNotFoundException {
        Alert alert = alertRepository.findOne(alertId);
        return Objects.nonNull(alert) ?
                new AlertToAlertDTOMapper().map(alert, new AlertDTO()) : new AlertDTO();
    }
}
