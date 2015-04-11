package com.tracegerm.tracegermws.controller;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.service.IAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by askos on 10/4/2015.
 */

@RestController
@RequestMapping(value = "/alerts")
public class AlertController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertController.class);

    private final IAlertService alertService;

    @Autowired
    public AlertController(IAlertService alertService) {
        this.alertService = alertService;
    }

    @RequestMapping(value = "/save/user/{username}/place/{placeId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Long> createAlert(@PathVariable String username, @PathVariable Long placeId, @RequestBody AlertDTO alertDTO) {
        LOGGER.info("Request for new alert with username:"+username);

        long alertID = alertService.createAlert(username, placeId, alertDTO);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(alertID, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{alertId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlertDTO> getAlert(@PathVariable long alertId)  throws ResourceNotFoundException {
        LOGGER.info("Request for place");

        AlertDTO alert = alertService.fetchPlaceByID(alertId);
        return new ResponseEntity<>(alert, HttpStatus.OK);
    }
}
