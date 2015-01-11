/**
 * 
 */
package com.tracegerm.tracegermws.service;

import java.util.Collection;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;

/**
 * @author askos
 *
 */
public interface IVisitDetailsService {
	
	long createVisitDetails(VisitDetailsDTO visitDetailsDTO);
	
	VisitDetailsDTO fetchVisiDetailsByID(long visitDetailsId) throws ResourceNotFoundException;
	
	Collection<VisitDetailsDTO> fetchVisitDetailsByUser(String username) throws ResourceNotFoundException;
}
