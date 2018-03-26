package com.tracegerm.tracegermws.rest;

import com.tracegerm.tracegermws.model.Alert;
import com.tracegerm.tracegermws.service.AlertService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${tracegerm.token.header}")
    private String tokenHeader;


    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Alert> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.findById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        alertService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Alert> save(@RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.create(alert));
    }

    @PutMapping("{id}")
    public ResponseEntity<Alert> update(@PathVariable Long id, @RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.save(alert));
    }

    @MessageMapping("/alert")
    @SendTo("/topic/alerts")
    public Alert alert(Alert alert) throws Exception {
        return alert;
    }

    @GetMapping("/isNotAccepted/false")
    public ResponseEntity<List<Alert>> getAlertsByAcceptedFalse() throws AuthenticationException {
        return ResponseEntity.ok(alertService.getAlertsByAcceptedFalse());
    }
}
