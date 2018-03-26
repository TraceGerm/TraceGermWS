package com.tracegerm.tracegermws.service.impl;

import com.tracegerm.tracegermws.config.SecurityContextProvider;
import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.repository.AlertRepository;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.service.AlertService;
import com.tracegerm.tracegermws.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    private final UserRepository userRepository;

    private final SecurityContextProvider securityContextProvider;

    private final MessageSendingOperations<String> messagingTemplate;

    private final NotificationService notificationService;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository, UserRepository userRepository, SecurityContextProvider securityContextProvider, MessageSendingOperations<String> messagingTemplate, NotificationService notificationService) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.securityContextProvider = securityContextProvider;
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }


    @Override
    public List<Alert> getAlertsByAcceptedFalse() {
        return alertRepository.findAlertsByAccepted(false);
    }

    @Override
    public Alert findById(Long id) {
        return alertRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Long id) {
        alertRepository.delete(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Alert save(Alert alert) {
        alert = alertRepository.save(alert);
        this.notificationService.createUserNotifications(alert);
        return alert;
    }

    @Override
    public Alert create(Alert alert) {
        alert.setUser(this.userRepository.findByUsername(securityContextProvider.getUserDetails().getUsername()));
        alert.setAccepted(false);
        Date date = new Date();
        alert.setTimestamp(new Timestamp(date.getTime()));
        alert = alertRepository.save(alert);
        this.messagingTemplate.convertAndSend("/topic/alerts", alert);
        return alert;
    }
}
