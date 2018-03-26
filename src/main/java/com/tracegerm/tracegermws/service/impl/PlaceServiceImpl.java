package com.tracegerm.tracegermws.service.impl;

import com.tracegerm.tracegermws.config.SecurityContextProvider;
import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.PlaceRepository;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    private final UserRepository userRepository;

    private final SecurityContextProvider securityContextProvider;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, UserRepository userRepository, SecurityContextProvider securityContextProvider) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.securityContextProvider = securityContextProvider;
    }

    @Override
    public List<Place> getLast10Places(User user) {
        return placeRepository.getLast10PlacesByUserId(user.getId());
    }

    @Override
    public Place save(Place place) {
        place.setUser(this.userRepository.findByUsername(securityContextProvider.getUserDetails().getUsername()));
        return placeRepository.save(place);
    }
}
