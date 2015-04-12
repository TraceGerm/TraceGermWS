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
public class VisitDetailsDTOtoVisitDetailsMapper implements IMapper<VisitDetailsDTO, VisitDetails>{

	@Override
	public VisitDetails map(VisitDetailsDTO source, VisitDetails target) {

		target.setTimestamp(source.getTimestamp());
		
		return target;
	}

}
