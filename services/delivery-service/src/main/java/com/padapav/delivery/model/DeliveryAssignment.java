package com.padapav.delivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_assignments")
@Data
public class DeliveryAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String riderName;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status = DeliveryStatus.ASSIGNED;
}
