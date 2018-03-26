/**
 *
 */
package com.tracegerm.tracegermws.repository;


import com.tracegerm.tracegermws.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * FROM places WHERE fk_user = :userId ORDER BY  timestamp DESC LIMIT 10", nativeQuery = true)
    List<Place> getLast10PlacesByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM places p WHERE p.fk_user = :userId AND p.timestamp >= now() - interval '2 days'", nativeQuery = true)
    List<Place> findPlacesOf48HoursByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM places p, users u WHERE ST_Point_Inside_Circle(p.position,:longitude,:latitude,10) " +
            "AND p.timestamp BETWEEN to_timestamp(:fromTime, 'YYYY/MM/DD hh24:mi:ss') " +
            "AND to_timestamp(:fromTime, 'YYYY/MM/DD hh24:mi:ss') + interval '10 minutes' AND p.fk_user != :userId " +
            "AND p.fk_user = u.user_id", nativeQuery = true)
    List<Place> findPlacesByPlaceAndTime(@Param("latitude") Double latitude, @Param("longitude") Double longitude,
                                         @Param("fromTime") String fromTime, @Param("userId") Long userId);
}
