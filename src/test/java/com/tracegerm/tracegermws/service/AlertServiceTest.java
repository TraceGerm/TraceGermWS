package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.config.SecurityContextProviderImpl;
import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.AlertRepository;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.service.impl.AlertServiceImpl;
import com.tracegerm.tracegermws.service.impl.NotificationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "test", authorities = {"ROLE_ADMIN"})
public class AlertServiceTest {

    @InjectMocks
    private AlertServiceImpl alertService;

    @Mock
    private AlertRepository alertRepository;

    private Alert alert = new Alert();

    private User user = new User();

    @Before
    public void setup() {
        initMocks(this);
        Alert alert = new Alert();
        alert.setId(1L);
        alert.setAccepted(false);

        user.setUsername("test");
    }

    @Test
    public void shouldFindByAccepted() {
        List<Alert> alerts = new ArrayList<>();
        alerts.add(alert);
        when(alertService.getAlertsByAcceptedFalse()).thenReturn(alerts);
        List<Alert> returned = alertService.getAlertsByAcceptedFalse();
        assertEquals(alerts.size(), returned.size());
        assertEquals(alerts.get(0), returned.get(0));
    }

    @Test
    public void shouldFindById() {
        when(alertService.findById(alert.getId())).thenReturn(alert);
        Alert returned = alertService.findById(alert.getId());
        assertEquals(alert, returned);
    }

}
