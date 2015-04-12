/**
 * 
 */
package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.user.User;

import java.util.Collection;

/**
 * @author askos
 *
 */
public interface IVisitDetailsService {
	
	long createVisitDetails(String username, Long placeId, VisitDetailsDTO visitDetailsDTO);
	
	VisitDetailsDTO fetchVisitDetailsByID(long visitDetailsId) throws ResourceNotFoundException;
	
	Collection<VisitDetailsDTO> fetchVisitDetailsByUser(User user) throws ResourceNotFoundException;
}
