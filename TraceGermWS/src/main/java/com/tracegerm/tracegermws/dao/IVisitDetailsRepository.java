/**
 * 
 */
package com.tracegerm.tracegermws.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

/**
 * @author askos
 *
 */
public interface IVisitDetailsRepository extends JpaRepository<VisitDetails, Long>{
	
	@Query("select * from visit_details where visit_details.fk_username = ?")
    List<VisitDetails> findVisitDetailsByUser(String username);

}
