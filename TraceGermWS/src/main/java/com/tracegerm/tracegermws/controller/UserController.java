package com.tracegerm.tracegermws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracegerm.tracegermws.dto.UserDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.service.IUserService;

@Controller
@RestController
@RequestMapping(value="/users")
public class UserController {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private IUserService userService;
  
  @Autowired
  public UserController(IUserService userService) {
		this.userService = userService;
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
	  LOGGER.info("Request for user creation with username: " + userDTO.getUsername());
		
		
	  userService.createUser(userDTO);
	  HttpHeaders headers = new HttpHeaders();
	  return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }
  
  @RequestMapping(value = "/{username}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> getUser(@PathVariable String username)  throws ResourceNotFoundException {
	  LOGGER.info("Request for user");
		
	  UserDTO user = userService.fetchUserByUsername(username);
	  return new ResponseEntity<>(user, HttpStatus.OK);
  }
}