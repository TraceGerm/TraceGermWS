/**
 * 
 */
package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.PlaceDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;

/**
 * @author askos
 *
 */
public interface IPlaceService {
	
	long createPlace (PlaceDTO placeDTO);
	
	PlaceDTO fetchPlaceByID(long placeId) throws ResourceNotFoundException;

}
