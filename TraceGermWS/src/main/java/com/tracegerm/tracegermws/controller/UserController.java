package com.tracegerm.tracegermws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.mapper.UserDTOtoUserMapper;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.service.IUserService;

@Controller
@RestController
@RequestMapping(value="/users")
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
	  LOGGER.info("Request for user creation with username: " + username);
    try {
      User user = new User(username);
      userService.createUser(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }
  
  @RequestMapping(value = "/saves", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<Void> createUser(@RequestParam String username, @RequestBody UserDTO userDTO) {
		LOGGER.info("Request for user creation with name: " + userDTO.getUsername(), username);
		
		User user = new UserDTOtoUserMapper().map(userDTO, new User(username));
		userService.createUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}