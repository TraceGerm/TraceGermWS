package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.AlertRepository;
import com.tracegerm.tracegermws.repository.NotificationRepository;
import com.tracegerm.tracegermws.service.impl.NotificationServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "test", authorities = {"ROLE_ADMIN"})
public class NotificationServiceTest {

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    private User user = new User();

    private Notification notification = new Notification();

    private Alert alert = new Alert();

    @Before
    public void setup() {
        initMocks(this);
        user.setUsername("test");
        Alert alert = new Alert();
        alert.setId(1L);
        alert.setAccepted(false);
        notification.setAlert(alert);
        notification.setUser(user);
        notification.setAccepted(false);
    }

    @Test
    public void shouldFindByAccepted() {
        notificationService.getNotAcceptedNotifications(user);
        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification);
        when(notificationService.getNotAcceptedNotifications(user)).thenReturn(notifications);

        List<Notification> returned = notificationService.getNotAcceptedNotifications(user);
        assertEquals(notifications.size(), returned.size());
        assertEquals(notifications.get(0), returned.get(0));
    }

}
