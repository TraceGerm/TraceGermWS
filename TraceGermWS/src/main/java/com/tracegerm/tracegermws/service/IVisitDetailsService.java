/**
 * 
 */
package com.tracegerm.tracegermws.service;

import java.util.Collection;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.model.user.User;

/**
 * @author askos
 *
 */
public interface IVisitDetailsService {
	
	long createVisitDetails(String username, VisitDetailsDTO visitDetailsDTO);
	
	VisitDetailsDTO fetchVisitDetailsByID(long visitDetailsId) throws ResourceNotFoundException;
	
	Collection<VisitDetailsDTO> fetchVisitDetailsByUser(User user) throws ResourceNotFoundException;
}
