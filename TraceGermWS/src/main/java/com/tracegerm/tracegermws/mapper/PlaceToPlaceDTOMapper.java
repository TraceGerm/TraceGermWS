/**
 * 
 */
package com.tracegerm.tracegermws.mapper;

import com.tracegerm.tracegermws.dto.PlaceDTO;
import com.tracegerm.tracegermws.model.place.Place;

/**
 * @author askos
 *
 */
public class PlaceToPlaceDTOMapper implements IMapper<Place, PlaceDTO>{

	
	@Override
	public PlaceDTO map(Place source, PlaceDTO target) {
		target.setId(source.getId());
		target.setLatitude(source.getLatitude());
		target.setLongitude(source.getLongitude());
		target.setAccuracy(source.getAccuracy());
		
		return target;
	}
	

}
