package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.Alert;
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
public class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    private Alert alert = new Alert();

    private User user = new User();

    private Alert createAlert() {
        alert.setUser(userRepository.findByUsername("test"));
        alert.setAccepted(false);
        alert.setTimestamp(new Timestamp(new Date().getTime()));
        return alert;
    }

    @Before
    public void setUp() {
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);
        alertRepository.save(createAlert());
    }

    @Test
    public void shouldFindAlertByTimestamp() {
        List<Alert> alerts = alertRepository.findAlertsByTimestamp(alert.getTimestamp());
        assertEquals(alerts.size(), 1);
    }

    @Test
    public void shouldFindAlertByAccepted() {
        List<Alert> alerts = alertRepository.findAlertsByAccepted(false);
        assertEquals(alerts.size(), 1);
    }

    @After
    public void tearDown() {
        alertRepository.delete(alert);
        userRepository.delete(user);
    }


}
