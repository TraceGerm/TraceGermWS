/**
 * 
 */
package com.tracegerm.tracegermws.controller;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.service.IVisitDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author askos
 *
 */
@RestController
@RequestMapping(value = "/visitDetails")
public class VisitDetailsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VisitDetailsController.class);

	private final IVisitDetailsService visitDetailsService;

	@Autowired
	public VisitDetailsController(IVisitDetailsService visitDetailsService) {
		this.visitDetailsService = visitDetailsService;
	}
	
	@RequestMapping(value = "/save{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createVisitDetails(@RequestParam String username, @RequestBody VisitDetailsDTO visitDetailsDTO) {
		LOGGER.info("Request for visit details creation for user with username:"+ username);
		
		visitDetailsService.createVisitDetails(username, visitDetailsDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{visitDetailsId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VisitDetailsDTO> getVisitDetails(@PathVariable Long visitDetailsId)  throws ResourceNotFoundException {
		LOGGER.info("Request for specific visit details");
		
		VisitDetailsDTO visitDetails = visitDetailsService.fetchVisitDetailsByID(visitDetailsId);
		return new ResponseEntity<>(visitDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<VisitDetailsDTO>> getVisitDetailsByUser(@PathVariable String username)  throws ResourceNotFoundException {
		LOGGER.info("Request for visit details for user");
		
		User user = new User(username);
		Collection<VisitDetailsDTO> visitDetailsList = visitDetailsService.fetchVisitDetailsByUser(user);
		return new ResponseEntity<>(visitDetailsList, HttpStatus.OK);
	}

}	
