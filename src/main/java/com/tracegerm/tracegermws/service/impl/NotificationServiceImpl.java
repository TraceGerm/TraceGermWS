package com.tracegerm.tracegermws.service.impl;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.NotificationRepository;
import com.tracegerm.tracegermws.repository.PlaceRepository;
import com.tracegerm.tracegermws.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final PlaceRepository placeRepository;

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, PlaceRepository placeRepository,
                                   MessageSendingOperations<String> messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.placeRepository = placeRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void createUserNotifications(Alert alert) {
        List<Place> places = this.placeRepository.findPlacesOf48HoursByUserId(alert.getUser().getId());
        List<Place> pl = new ArrayList<>();
        for (Place place : places) {
            pl.addAll(this.placeRepository.findPlacesByPlaceAndTime(place.getPosition().getY(), place.getPosition().getX(),
                    place.getTimestamp().toString().split("\\.")[0], alert.getUser().getId()));
        }
        Set<Notification> notifications = new HashSet<>();
        for (Place pll : pl) {
            Notification notification = new Notification();
            notification.setAccepted(false);
            notification.setUser(alert.getUser());
            notification.setAlert(alert);
            notifications.add(notification);
        }
        List<Notification> savedNotifications = this.notificationRepository.save(notifications);
        this.sendUserNotifications(savedNotifications);
    }

    @Override
    public void sendUserNotifications(List<Notification> notifications) {
        for (Notification notification : notifications) {
            this.messagingTemplate.convertAndSend("/topic/notifications/" + notification.getUser().getUsername(), notification);
        }
    }

    @Override
    public List<Notification> getNotAcceptedNotifications(User user) {
        return this.notificationRepository.findNotificationsByUserAndIsNotAccepted(user.getId());
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}