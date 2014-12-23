package com.tracegerm.tracegermws.dao;

import com.tracegerm.tracegermws.model.user.User;

public interface IUserDao {
	
	boolean save (User user);
	
	boolean update (User user);
	
	boolean delete (User user);
	
	User findById (long userId);

}
