/**
 * 
 */
package com.tracegerm.tracegermws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracegerm.tracegermws.dto.PlaceDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.service.IPlaceService;

/**
 * @author askos
 *
 */
@RestController
@RequestMapping(value = "/places")
public class PlaceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

	private final IPlaceService placeService;

	@Autowired
	public PlaceController(IPlaceService placeService) {
		this.placeService = placeService;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> createPlace(@RequestBody PlaceDTO placeDTO) {	
		LOGGER.info("Request for new place register");			
			
		placeService.createPlace(placeDTO);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	  
	@RequestMapping(value = "/{placeId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlaceDTO> getPlace(@PathVariable long placeId)  throws ResourceNotFoundException {
		LOGGER.info("Request for place");
			
		PlaceDTO place = placeService.fetchPlaceByID(placeId);
		return new ResponseEntity<>(place, HttpStatus.OK);
	}
}


