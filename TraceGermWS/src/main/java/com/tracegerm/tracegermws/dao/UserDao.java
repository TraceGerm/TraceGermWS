package com.tracegerm.tracegermws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.model.user.User;

@Component
@Transactional
public class UserDao implements IUserDao{
  
  @Autowired
  private SessionFactory sessionFactory;
  private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDao.class);

  public SessionFactory getSessionFactory() {
		return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
  }
	
	
  @Override
  public boolean save(User user) {
	  try {
	  sessionFactory.getCurrentSession().save(user);
	  return true;
	  } catch (final HibernateException ex) {
		  LOGGER.warn(
					"Hibernate exception was raised while trying to save business, info: "
							+ ex.getLocalizedMessage(), ex);
	  }
	  return false;
  }
  
  @Override
  public boolean delete(User user) {
	  try {
		  sessionFactory.getCurrentSession().delete(user);
		  return true;
	  } catch (final HibernateException ex) {
		  LOGGER.warn(
					"Hibernate exception was raised while trying to save business, info: "
							+ ex.getLocalizedMessage(), ex);
	  }
	  return false;
  }
  
  @Override
  public User findById(long id) {
	  User user = null;
	  try {
		  user = (User) sessionFactory.getCurrentSession()
				  .get(User.class, id);
	  } catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to find business by id, info: "
							+ ex.getLocalizedMessage(), ex);
	  }
    return user;
  }
  
  @Override
  public boolean update(User user) {
	  try {
	  sessionFactory.getCurrentSession().update(user);
	  return true;
	  } catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update business, info: "
							+ ex.getLocalizedMessage(), ex);
	}
	return false;
  }

}
