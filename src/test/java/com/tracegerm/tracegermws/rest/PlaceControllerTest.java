package com.tracegerm.tracegermws.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracegerm.tracegermws.AbstractRestControllerTest;
import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.AlertRepository;
import com.tracegerm.tracegermws.repository.PlaceRepository;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
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
public class PlaceControllerTest extends AbstractRestControllerTest {

    @Value("/api/places")
    private String alertsEndpoint;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    private Place place = new Place();

    private User user = new User();

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    private Place createPlace() {
        place.setPosition(geometryFactory.createPoint(new Coordinate(1d, 1d)));
        place.setAccuracy(1);
        place.setTimestamp(new Timestamp(new Date().getTime()));
        place.setUser(userRepository.findByUsername("test"));

        return place;
    }

    @Before
    public void setUp() {
        user.setUsername("test");
        user.setPassword("test");
        userRepository.save(user);
        placeRepository.save(createPlace());
    }

    @Test
    public void whenGetLast10PlacesByToken_thenAllAreReturned() throws Exception {
        this.mockMvc.perform(get(alertsEndpoint + "/user/lastPlaces"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", not(empty())));
    }

    @After
    public void tearDown() {
        placeRepository.delete(place);
        userRepository.delete(user);
    }


}
