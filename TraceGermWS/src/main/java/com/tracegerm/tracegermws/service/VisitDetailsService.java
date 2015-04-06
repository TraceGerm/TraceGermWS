/**
 * 
 */
package com.tracegerm.tracegermws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tracegerm.tracegermws.dto.VisitDetailsDTO;
import com.tracegerm.tracegermws.exception.ResourceNotFoundException;
import com.tracegerm.tracegermws.mapper.IMapper;
import com.tracegerm.tracegermws.mapper.VisitDetailsDTOtoVisitDetailsMapper;
import com.tracegerm.tracegermws.mapper.VisitDetailsToVisitDetailsDTOMapper;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;
import com.tracegerm.tracegermws.repository.IUserRepository;
import com.tracegerm.tracegermws.repository.IVisitDetailsRepository;

/**
 * @author askos
 *
 */
@Service
@Transactional
public class VisitDetailsService implements IVisitDetailsService{

	private final IVisitDetailsRepository visitDetailsRepository;
	private final IUserRepository userRepository;

	@Autowired
	public VisitDetailsService(IVisitDetailsRepository visitDetailsRepository, IUserRepository userRepository) {
		this.visitDetailsRepository = visitDetailsRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long createVisitDetails(String username, VisitDetailsDTO visitDetailsDTO) {
		VisitDetails visitDetails = new VisitDetailsDTOtoVisitDetailsMapper().map(visitDetailsDTO, new VisitDetails());
		visitDetails.setUser(userRepository.findOne(username)); 
		visitDetailsRepository.save(visitDetails);
		return visitDetails.getId();
	}
	
	@Transactional(readOnly = true)
	@Override
	public VisitDetailsDTO fetchVisitDetailsByID(long visitDetailsId)
			throws ResourceNotFoundException {
		VisitDetails visitDetails = visitDetailsRepository.findOne(visitDetailsId);
		return Objects.nonNull(visitDetails) ?
				new VisitDetailsToVisitDetailsDTOMapper().map(visitDetails, new VisitDetailsDTO()) : new VisitDetailsDTO();
		
	}

	
	@Transactional(readOnly = true)
	@Override
	public Collection<VisitDetailsDTO> fetchVisitDetailsByUser(User user)
			throws ResourceNotFoundException {
				
		List<VisitDetails> userVisitDetailsList = visitDetailsRepository.findVisitDetailsByUser(user);
		List<VisitDetailsDTO> userVisitDetails = new ArrayList<>();
		
		IMapper<VisitDetails, VisitDetailsDTO> mapper = new VisitDetailsToVisitDetailsDTOMapper();
		userVisitDetailsList.forEach((visitDetails) -> {
			userVisitDetails.add(mapper.map(visitDetails, new VisitDetailsDTO()));
		});
		return userVisitDetails;
	}
}
	
