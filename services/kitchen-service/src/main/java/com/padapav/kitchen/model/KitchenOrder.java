package com.padapav.kitchen.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "kitchen_orders")
@Data
public class KitchenOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private KitchenStatus status = KitchenStatus.NEW;

    private String notes;
}
