package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.Application;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.alert.Alert;
import com.tracegerm.tracegermws.model.place.Place;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

/**
 * Created by askos on 10/4/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AlertRepositoryTest extends DbUnitTestCase {

    @Autowired
    private IAlertRepository alertRepository;
    @Autowired
    private IPlaceRepository placeRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IVisitDetailsRepository detailsRepository;

    @Test
    public void testExistsFindById() throws ResourceNotFoundException {
        boolean alertExists = alertRepository.exists(1L);
        assertTrue(alertExists);
    }

    @Test
    public void testAlertNotFoundById() throws ResourceNotFoundException {
        boolean alertExists = alertRepository.exists(5200L);
        assertFalse(alertExists);
    }

   @Test
    public void testAlertCreation() {
        Alert alert = new Alert();
        java.util.Date date= new java.util.Date();
        alert.setTimestamp(new Timestamp(date.getTime()));
        alert.setPlace(placeRepository.findOne(1L));
        alert.setUser(userRepository.findOne("USER1"));
        alertRepository.save(alert);
        assertNotNull(alert.getId());
    }
}
