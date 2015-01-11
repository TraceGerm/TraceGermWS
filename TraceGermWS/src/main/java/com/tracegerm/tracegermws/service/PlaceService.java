/**
 * 
 */
package com.tracegerm.tracegermws.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.dao.IPlaceRepository;
import com.tracegerm.tracegermws.dto.PlaceDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.mapper.PlaceDTOtoPlaceMapper;
import com.tracegerm.tracegermws.mapper.PlaceToPlaceDTOMapper;
import com.tracegerm.tracegermws.model.place.Place;

/**
 * @author askos
 *
 */
@Service
@Transactional
public class PlaceService implements IPlaceService{

	private final IPlaceRepository placeRepository;

	@Autowired
	public PlaceService(IPlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long createPlace(PlaceDTO placeDTO) {
		Place place = new PlaceDTOtoPlaceMapper().map(placeDTO, new Place());
		place = placeRepository.save(place);

		return place.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public PlaceDTO fetchPlaceByID(long placeId) throws ResourceNotFoundException {
		Place place = placeRepository.findOne(placeId);
		return Objects.nonNull(place) ?
				new PlaceToPlaceDTOMapper().map(place, new PlaceDTO()) : new PlaceDTO();
	}

}
