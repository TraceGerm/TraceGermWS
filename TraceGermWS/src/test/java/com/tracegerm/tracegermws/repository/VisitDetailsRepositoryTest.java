/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tracegerm.tracegermws.Application;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

/**
 * @author askos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class VisitDetailsRepositoryTest extends DbUnitTestCase {
	
	@Autowired
	private IVisitDetailsRepository visitDetailsRepository;
	
	@Autowired
	private IPlaceRepository placeRepository;
	
	@Test
	public void testExistsFindById() throws ResourceNotFoundException {
		boolean visitDetailsExists = visitDetailsRepository.exists(1L);
		assertTrue(visitDetailsExists);
	}

	@Test
	public void testVisitDetailsNotFoundById() throws ResourceNotFoundException {
		boolean visitDetailsExists = visitDetailsRepository.exists(5000L);
		assertFalse(visitDetailsExists);
	}
	
	@Test
	public void testExistsFindByUser() throws ResourceNotFoundException {
		User user = new User("USER1");
		assertNotNull(visitDetailsRepository.findVisitDetailsByUser(user).get(0).getId());
	}
	
	@Test
	public void testVisitDetailsNotFoundByUser() throws ResourceNotFoundException {
		User user = new User("USER_NOT_EXIST");
		List<VisitDetails> visitDetails = visitDetailsRepository.findVisitDetailsByUser(user);
		assertTrue(visitDetails.isEmpty());		
	}
	
	@Test 
	public void testVisitDetailsCreationAfterPlace() {
		Place place = placeRepository.findOne(1L);
		User user = new User ("USER1");
		VisitDetails visitDetails = new VisitDetails();
		visitDetails.setUser(user);
		java.util.Date date= new java.util.Date();
		visitDetails.setTimeStamp(new Timestamp(date.getTime()));
		place.getDetailsList().add(visitDetails);
		placeRepository.save(place);
		assertEquals("USER1",visitDetailsRepository.findVisitDetailsByUser(user).get(0).getUser().getUsername());
	}
}
