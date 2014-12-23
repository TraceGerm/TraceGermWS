package com.tracegerm.tracegermws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

@Autowired
  private IUserService userService;
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(String username) {
    try {
      User user = new User(username);
      userService.deleteUser(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully deleted!";
  }

  @RequestMapping(value="/save")
  @ResponseBody
  public String create(String username) {
	  LOGGER.info("Request for user creation with username: " + username +"");
    try {
      User user = new User(username);
      userService.createUser(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }

}