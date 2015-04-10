/**
 * 
 */
package com.tracegerm.tracegermws.repository;

import com.tracegerm.tracegermws.model.user.User;
import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tracegerm.tracegermws.model.place.Place;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author askos
 *
 */
public interface IPlaceRepository extends JpaRepository<Place, Long>{
    

}
