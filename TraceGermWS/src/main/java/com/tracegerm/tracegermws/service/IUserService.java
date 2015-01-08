package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.user.User;

public interface IUserService {
	
	User fetchUserById(String Username) ;
	
	void createUser(User user);
	
	void deleteUser(User user);

}
