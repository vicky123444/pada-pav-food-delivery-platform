package com.padapav.kitchen.service;

import com.padapav.kitchen.model.KitchenOrder;
import com.padapav.kitchen.model.KitchenStatus;
import com.padapav.kitchen.repository.KitchenOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class KitchenService {

    private final KitchenOrderRepository kitchenOrderRepository;

    private static final Map<KitchenStatus, Set<KitchenStatus>> VALID_TRANSITIONS = Map.of(
        KitcenStatus.NEW, Set.of(KitchenStatus.ACCEPTED, KitchenStatus.CANCELLED),
        KitchenStatus.ACCEPTED, Set.of(KitchenStatus.PREPARING, KitchenStatus.CANCELLED),
        KitchenStatus.PREPARING, Set.of(KitchenStatus.READY, KitchenStatus.CANCELLED),
        KitchenStatus.READY, Set.of(),
        KitchenStatus.CANCELLED, Set.of()
    );

    public KitchenService(KitchenOrderRepository kitchenOrderRepository) {
        this.kitchenOrderRepository = kitchenOrderRepository;
    }

    public List<KitchenOrder> getPendingOrders() {
        return kitchenOrderRepository.findAll();
    }

    public KitchenOrder createOrder(Long orderId, String notes) {
        KitchenOrder kitchenOrder = new KitchenOrder();
        kitchenOrder.setOrderId(orderId);
        kitchenOrder.setNotes(notes == null ? "New kitchen ticket" : notes);
        kitchenOrder.setStatus(KitchenStatus.NEW);
        return kitchenOrderRepository.save(kitchenOrder);
    }

    public KitchenOrder acceptOrder(Long id) {
        KitchenOrder order = findById(id);
        return transition(order, KitchenStatus.ACCEPTED);
    }

    public KitchenOrder markReady(Long id) {
        KitchenOrder order = findById(id);
        return transition(order, KitchenStatus.READY);
    }

    public KitchenOrder startPreparing(Long id) {
        KitchenOrder order = findById(id);
        return transition(order, KitchenStatus.PREPARING);
    }

    public KitchenOrder cancelOrder(Long id) {
        KitchenOrder order = findById(id);
        return transition(order, KitchenStatus.CANCELLED);
    }

    private KitchenOrder findById(Long id) {
        return kitchenOrderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Kitchen order not found: " + id));
    }

    private KitchenOrder transition(KitchenOrder order, KitchenStatus next) {
        if (!VALID_TRANSITIONS.getOrDefault(order.getStatus(), Set.of()).contains(next)) {
            throw new IllegalArgumentException(
                "Invalid kitchen transition from " + order.getStatus() + " to " + next
            );
        }
        order.setStatus(next);
        return kitchenOrderRepository.save(order);
    }
}
