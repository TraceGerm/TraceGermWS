package com.tracegerm.tracegermws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.dao.IUserRepository;
import com.tracegerm.tracegermws.model.user.User;

@Component
@Transactional
@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userDao;
	

	@Override
	public void createUser(User user) {
		userDao.save(user);		
	}
	
	@Override 
	public void deleteUser(User user) {
		userDao.delete(user);
	}
	
	public IUserRepository getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserRepository userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see com.tracegerm.tracegermws.service.IUserService#fetchUserById(java.lang.String)
	 */
	@Override
	public User fetchUserById(String Username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
