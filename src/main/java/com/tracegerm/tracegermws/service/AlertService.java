package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.Alert;

import java.util.List;

public interface AlertService {

    List<Alert> getAlertsByAcceptedFalse();

    Alert findById(Long id);

    void deleteById(Long id);

    Alert save(Alert alert);

    Alert create(Alert alert);
}
