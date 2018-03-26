package com.tracegerm.tracegermws.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracegerm.tracegermws.AbstractRestControllerTest;
import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.AlertRepository;
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
public class AlertControllerTest extends AbstractRestControllerTest {

    @Value("/api/alerts")
    private String alertsEndpoint;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

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
//        user.setUsername("test");
//        user.setPassword("test");
//        userRepository.save(user);
        alertRepository.save(createAlert());

    }

    @Test
    public void whenNonAcceptedAlertsRequested_thenAllAreReturned() throws Exception {
        this.mockMvc.perform(get(alertsEndpoint + "/isNotAccepted/false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void whenAlertRequested_thenIsReturned() throws Exception {
        this.mockMvc.perform(get(alertsEndpoint + "/" + alert.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    @WithMockUser(username = "test", authorities = {"ROLE_ADMIN"})
    public void whenAlertUpdated_thenIsReturnedUpdated() throws Exception {
        alert.setAccepted(true);
        this.mockMvc.perform(put(alertsEndpoint + "/" + alert.getId())
                .contentType(contentType)
                .content(mapper.writeValueAsString(alert)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$.accepted").value(true));
    }

    @Test(expected = NestedServletException.class)
    @WithMockUser(username = "test", authorities = {"ROLE_USER"})
    public void whenAlertUpdatedWithNoRole_thenItFails() throws Exception {
        alert.setAccepted(true);
        this.mockMvc.perform(put(alertsEndpoint + "/" + alert.getId())
                .contentType(contentType)
                .content(mapper.writeValueAsString(alert)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$.accepted").value(true));
    }

//    @After
//    public void tearDown() {
//        alertRepository.delete(alert);
//        userRepository.delete(user);
//    }


}
