package com.tracegerm.tracegermws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.dao.IUserDao;
import com.tracegerm.tracegermws.model.user.User;

@Component
@Transactional
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Transactional(readOnly = true)
	@Override
	public User fetchUserById(long userId) {
		return userDao.findById(userId);
	}

	@Override
	public void createUser(User user) {
		userDao.save(user);		
	}
	
	@Override 
	public void deleteUser(User user) {
		userDao.delete(user);
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
