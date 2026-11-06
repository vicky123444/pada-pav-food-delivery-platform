package com.padapav.kitchen.controller;

import com.padapav.kitchen.model.KitchenOrder;
import com.padapav.kitchen.service.KitchenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KitchenController {

    private final KitchenService kitchenService;

    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping("/kitchen/orders")
    public List<KitchenOrder> getPendingOrders() {
        return kitchenService.getPendingOrders();
    }

    @PostMapping("/kitchen/orders")
    public KitchenOrder createKitchenOrder(@RequestParam Long orderId, @RequestParam(required = false) String notes) {
        return kitchenService.createOrder(orderId, notes);
    }

    @PatchMapping("/kitchen/orders/{id}/accept")
    public KitchenOrder acceptOrder(@PathVariable Long id) {
        return kitchenService.acceptOrder(id);
    }

    @PatchMapping("/kitchen/orders/{id}/preparing")
    public KitchenOrder startPreparing(@PathVariable Long id) {
        return kitchenService.startPreparing(id);
    }

    @PatchMapping("/kitchen/orders/{id}/ready")
    public KitchenOrder markReady(@PathVariable Long id) {
        return kitchenService.markReady(id);
    }

    @PatchMapping("/kitchen/orders/{id}/cancel")
    public KitchenOrder cancelOrder(@PathVariable Long id) {
        return kitchenService.cancelOrder(id);
    }
}
