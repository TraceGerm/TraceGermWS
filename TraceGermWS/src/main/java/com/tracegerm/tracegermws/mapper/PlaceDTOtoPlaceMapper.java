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
public class PlaceDTOtoPlaceMapper implements IMapper<PlaceDTO, Place>{


	@Override
	public Place map(PlaceDTO source, Place target) {
		target.setLatitude(source.getLatitude());
		target.setLongitude(source.getLongitude());
		target.setAccuracy(source.getAccuracy());
		return target;
	}

}
