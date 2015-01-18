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
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.repository.IUserRepository;

/**
 * @author askos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepositoryTest extends DbUnitTestCase {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void testExistsFindByUsername() throws ResourceNotFoundException {
		boolean userExists = userRepository.exists("USER1");
		assertTrue(userExists);
	}
	
	@Test
	public void testUserNotFoundByUsername() throws ResourceNotFoundException {
		boolean userExists = userRepository.exists("RANDOM");
		assertFalse(userExists);
	}
	
	@Test
	public void testUserCreation() {
		User user = new User("USER_TEST");
		userRepository.save(user);
		User savedUser= userRepository.findOne("USER_TEST");
		assertNotNull(savedUser.getUsername());
	}

}
