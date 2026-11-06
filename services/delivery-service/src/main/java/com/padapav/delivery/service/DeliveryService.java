package com.padapav.delivery.service;

import com.padapav.delivery.model.DeliveryAssignment;
import com.padapav.delivery.model.DeliveryStatus;
import com.padapav.delivery.repository.DeliveryAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DeliveryService {

    private final DeliveryAssignmentRepository deliveryAssignmentRepository;

    private static final Map<DeliveryStatus, Set<DeliveryStatus>> VALID_TRANSITIONS = Map.of(
        DeliveryStatus.ASSIGNED, Set.of(DeliveryStatus.PICKED_UP, DeliveryStatus.CANCELLED),
        DeliveryStatus.PICKED_UP, Set.of(DeliveryStatus.OUT_FOR_DELIVERY, DeliveryStatus.CANCELLED),
        DeliveryStatus.OUT_FOR_DELIVERY, Set.of(DeliveryStatus.DELIVERED, DeliveryStatus.CANCELLED),
        DeliveryStatus.DELIVERED, Set.of(),
        DeliveryStatus.CANCELLED, Set.of()
    );

    public DeliveryService(DeliveryAssignmentRepository deliveryAssignmentRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
    }

    public List<DeliveryAssignment> listAssignments() {
        return deliveryAssignmentRepository.findAll();
    }

    public DeliveryAssignment assignOrder(Long orderId, String riderName) {
        DeliveryAssignment assignment = new DeliveryAssignment();
        assignment.setOrderId(orderId);
        assignment.setRiderName(riderName);
        assignment.setStatus(DeliveryStatus.ASSIGNED);
        return deliveryAssignmentRepository.save(assignment);
    }

    public DeliveryAssignment updateStatus(Long id, DeliveryStatus nextStatus) {
        DeliveryAssignment assignment = deliveryAssignmentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Delivery assignment not found: " + id));

        if (!VALID_TRANSITIONS.getOrDefault(assignment.getStatus(), Set.of()).contains(nextStatus)) {
            throw new IllegalArgumentException(
                "Invalid delivery transition from " + assignment.getStatus() + " to " + nextStatus
            );
        }

        assignment.setStatus(nextStatus);
        return deliveryAssignmentRepository.save(assignment);
    }
}
