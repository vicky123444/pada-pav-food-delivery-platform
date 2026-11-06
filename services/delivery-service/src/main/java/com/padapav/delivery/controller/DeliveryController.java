package com.padapav.delivery.controller;

import com.padapav.delivery.model.DeliveryAssignment;
import com.padapav.delivery.model.DeliveryStatus;
import com.padapav.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery/assignments")
    public List<DeliveryAssignment> listAssignments() {
        return deliveryService.listAssignments();
    }

    @PostMapping("/delivery/assignments")
    public DeliveryAssignment assignOrder(@RequestParam Long orderId, @RequestParam(required = false) String riderName) {
        return deliveryService.assignOrder(orderId, riderName == null ? "Rider-" + orderId : riderName);
    }

    @PatchMapping("/delivery/assignments/{id}/status")
    public DeliveryAssignment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return deliveryService.updateStatus(id, DeliveryStatus.valueOf(status));
    }
}
