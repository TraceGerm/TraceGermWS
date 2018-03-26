package com.tracegerm.tracegermws.rest;

import com.tracegerm.tracegermws.config.SecurityContextProvider;
import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.service.PlaceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    private final PlaceService placeService;

    private final SecurityContextProvider securityContextProvider;

    @Autowired
    public PlaceController(PlaceService placeService, SecurityContextProvider securityContextProvider) {
        this.placeService = placeService;
        this.securityContextProvider = securityContextProvider;
    }

    @GetMapping("/user/lastPlaces")
    public ResponseEntity<List<Place>> getLast10PlacesByToken() throws AuthenticationException {
        User user = this.userRepository.findByUsername(securityContextProvider.getUserDetails().getUsername());
        return ResponseEntity.ok(placeService.getLast10Places(user));
    }

    @PostMapping
    public ResponseEntity<Place> save(@RequestBody Place place) {
        return ResponseEntity.ok(placeService.save(place));
    }
}
