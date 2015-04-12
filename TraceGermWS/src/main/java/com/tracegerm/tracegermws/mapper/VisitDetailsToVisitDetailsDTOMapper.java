/**
 * 
 */
package com.tracegerm.tracegermws.mapper;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

/**
 * @author askos
 *
 */
public class VisitDetailsToVisitDetailsDTOMapper implements IMapper<VisitDetails, VisitDetailsDTO>{

	@Override
	public VisitDetailsDTO map(VisitDetails source, VisitDetailsDTO target) {
		target.setTimestamp(source.getTimestamp());
		
		return target;
	}

}
