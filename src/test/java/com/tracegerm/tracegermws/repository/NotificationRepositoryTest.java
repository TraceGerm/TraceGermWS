package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlertRepository alertRepository;

    private Alert alert = new Alert();

    private Notification notification = new Notification();

    private User user = new User();

    private Alert createAlert() {
        alert.setUser(userRepository.findByUsername("test"));
        alert.setAccepted(false);
        alert.setTimestamp(new Timestamp(new Date().getTime()));
        return alert;
    }

    private Notification createNotification() {
        notification.setAccepted(false);
        notification.setAlert(alert);
        notification.setUser(user);
        return notification;
    }

    @Before
    public void setUp() {
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);
        alertRepository.save(createAlert());
        notificationRepository.save(createNotification());
    }

    @Test
    public void shouldFindNotificationBy() {
        List<Notification> notifications = notificationRepository.findNotificationsByUserAndIsNotAccepted(user.getId());
        assertEquals(1, notifications.size());
    }

    @After
    public void tearDown() {
        notificationRepository.delete(notification);
        alertRepository.delete(alert);
        userRepository.delete(user);
    }


}
