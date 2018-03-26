package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.User;

import java.util.List;

public interface NotificationService {

    void createUserNotifications(Alert alert);

    void sendUserNotifications(List<Notification> notifications);

    List<Notification> getNotAcceptedNotifications(User user);

    Notification save(Notification notification);
}
