/**
 * 
 */
package com.tracegerm.tracegermws.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracegerm.tracegermws.model.place.Place;



/**
 * @author askos
 *
 */
public interface IPlaceRepository extends JpaRepository<Place, Long>{

}
