package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.mapper.AlertDTOtoAlertMapper;
import com.tracegerm.tracegermws.mapper.AlertToAlertDTOMapper;
import com.tracegerm.tracegermws.model.alert.Alert;
import com.tracegerm.tracegermws.repository.IAlertRepository;
import com.tracegerm.tracegermws.repository.IPlaceRepository;
import com.tracegerm.tracegermws.repository.IUserRepository;
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
    private final IUserRepository userRepository;
    private final IPlaceRepository placeRepository;

    @Autowired
    public AlertService(IAlertRepository alertRepository, IUserRepository userRepository, IPlaceRepository placeRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Long createAlert(String username, Long placeId, AlertDTO alertDTO) {
        Alert alert = new AlertDTOtoAlertMapper().map(alertDTO, new Alert());
        alert.setUser(userRepository.findOne(username));
        alert.setPlace(placeRepository.findOne(placeId));
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
