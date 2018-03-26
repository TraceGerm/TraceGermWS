package com.tracegerm.tracegermws.repository;


import com.tracegerm.tracegermws.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT * FROM notifications WHERE fk_user = :userId AND accepted = FALSE", nativeQuery = true)
    List<Notification> findNotificationsByUserAndIsNotAccepted(@Param("userId") Long user);

}

