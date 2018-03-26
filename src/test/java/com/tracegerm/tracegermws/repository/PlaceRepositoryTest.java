package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
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
public class PlaceRepositoryTest {

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
    public void shouldFindPlacesOf48HoursByUserId() {
        List<Place> places = placeRepository.findPlacesOf48HoursByUserId(user.getId());
        assertEquals(places.size(), 1);
    }

    @Test
    public void shouldGetLast10PlacesByUserId() {
        List<Place> places = placeRepository.getLast10PlacesByUserId(user.getId());
        assertEquals(places.size(), 1);
    }

//    @After
//    public void tearDown() {
//        placeRepository.delete(place);
//        userRepository.delete(user);
//    }


}
