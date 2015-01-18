package com.tracegerm.tracegermws.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.mapper.UserDTOtoUserMapper;
import com.tracegerm.tracegermws.mapper.UserToUserDTOMapper;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.repository.IUserRepository;

@Component
@Transactional
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public String createUser(UserDTO userDTO) {
		User user = new UserDTOtoUserMapper().map(userDTO, new User());
		user = userRepository.save(user);

		return user.getUsername();
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public UserDTO fetchUserByUsername(String username) throws ResourceNotFoundException{
		User user = userRepository.findOne(username);
		return Objects.nonNull(user) ?
				new UserToUserDTOMapper().map(user, new UserDTO()) : new UserDTO();
	}

	
}
