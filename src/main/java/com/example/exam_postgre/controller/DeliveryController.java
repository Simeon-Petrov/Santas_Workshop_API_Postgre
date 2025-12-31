package com.example.exam_postgre.controller;

import com.example.exam_postgre.dto.DeliveryRequest;
import com.example.exam_postgre.model.Delivery;
import com.example.exam_postgre.model.DeliveryStatus;
import com.example.exam_postgre.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deliveries")
// ПРЕМАХНАТО: @RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@Valid @RequestBody DeliveryRequest request) {
        Delivery createdDelivery = deliveryService.createDelivery(request);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        DeliveryStatus newStatus = DeliveryStatus.fromString(body.get("deliveryStatus"));
        return ResponseEntity.ok(deliveryService.updateDeliveryStatus(id, newStatus));
    }
}