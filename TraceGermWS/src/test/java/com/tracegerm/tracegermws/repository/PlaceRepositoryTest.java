/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tracegerm.tracegermws.Application;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.repository.IPlaceRepository;

/**
 * @author askos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PlaceRepositoryTest extends DbUnitTestCase {
	
	@Autowired
	private IPlaceRepository placeRepository;
	
	@Test
	public void testExistsFindById() throws ResourceNotFoundException {
		boolean placeExists = placeRepository.exists(1L);
		assertTrue(placeExists);
	}

	@Test
	public void testPlaceNotFoundById() throws ResourceNotFoundException {
		boolean placeExists = placeRepository.exists(5200L);
		assertFalse(placeExists);
	}
	
	@Test
	public void testPlaceCreation() {
		Place place = new Place();
		place.setAccuracy((float) 52.534);
		assertNull(place.getId());
		place = placeRepository.save(place);
		assertNotNull(place.getId());
	}
}
