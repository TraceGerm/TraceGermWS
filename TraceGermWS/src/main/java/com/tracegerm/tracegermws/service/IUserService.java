package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;

public interface IUserService {
	
	UserDTO fetchUserByUsername(String Username) throws ResourceNotFoundException;
	
	String createUser(UserDTO userDTO);
	

}
