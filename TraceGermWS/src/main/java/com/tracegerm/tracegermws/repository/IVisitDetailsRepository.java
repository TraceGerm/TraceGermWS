/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

/**
 * @author askos
 *
 */
public interface IVisitDetailsRepository extends JpaRepository<VisitDetails, Long>{
	
	@Query("select details from VisitDetails details where details.user = ?1")
    List<VisitDetails> findVisitDetailsByUser(User user);

}
