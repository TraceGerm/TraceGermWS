package com.tracegerm.tracegermws.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracegerm.tracegermws.AbstractRestControllerTest;
import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.AlertRepository;
import com.tracegerm.tracegermws.repository.NotificationRepository;
import com.tracegerm.tracegermws.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.util.NestedServletException;

import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "test", authorities = {"ROLE_ADMIN"})
public class NotificationControllerTest extends AbstractRestControllerTest {

    @Value("/api/notifications")
    private String alertsEndpoint;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    private Alert alert = new Alert();

    private User user = new User();

    private Notification notification = new Notification();

    private Alert createAlert() {
        alert.setUser(userRepository.findByUsername("test"));
        alert.setAccepted(false);
        alert.setTimestamp(new Timestamp(new Date().getTime()));
        return alert;
    }

    private Notification createNotification() {
        notification.setAlert(alert);
        notification.setUser(user);
        notification.setAccepted(false);
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
    public void whenNonAcceptedNotificationsRequested_thenAllAreReturned() throws Exception {
        this.mockMvc.perform(get(alertsEndpoint + "/user/notAcceptedNotifications"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())));
    }

    @After
    public void tearDown() {
        notificationRepository.delete(notification);
        alertRepository.delete(alert);
        userRepository.delete(user);
    }


}
