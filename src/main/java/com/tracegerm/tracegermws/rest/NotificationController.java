package com.tracegerm.tracegermws.rest;

import com.tracegerm.tracegermws.config.SecurityContextProvider;
import com.tracegerm.tracegermws.model.Notification;
import com.tracegerm.tracegermws.model.User;
import com.tracegerm.tracegermws.repository.UserRepository;
import com.tracegerm.tracegermws.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final UserRepository userRepository;

    private final NotificationService notificationService;

    private final SecurityContextProvider securityContextProvider;

    @Autowired
    public NotificationController(UserRepository userRepository, NotificationService notificationService, SecurityContextProvider securityContextProvider) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.securityContextProvider = securityContextProvider;
    }

    @GetMapping("/user/notAcceptedNotifications")
    public ResponseEntity<List<Notification>> getNotAcceptedNotifications() throws AuthenticationException {
        User user = this.userRepository.findByUsername(securityContextProvider.getUserDetails().getUsername());
        return ResponseEntity.ok(notificationService.getNotAcceptedNotifications(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<Notification> save(@PathVariable Long id, @RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.save(notification));
    }
}
