package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.Place;
import com.tracegerm.tracegermws.model.User;

import java.util.List;

public interface PlaceService {

    List<Place> getLast10Places(User user);

    Place save(Place place);
}
