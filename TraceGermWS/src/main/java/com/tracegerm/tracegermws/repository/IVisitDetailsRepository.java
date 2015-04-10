/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.place.Place;
import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author askos
 *
 */
public interface IVisitDetailsRepository extends JpaRepository<VisitDetails, Long>{

	@Query("select details from VisitDetails details where details.user = ?1")
    List<VisitDetails> findVisitDetailsByUser(User user);

    @Query("select details from VisitDetails details where details.place = ?1")
    List<VisitDetails> findVisitDetailsByPlace(Place place);

}
