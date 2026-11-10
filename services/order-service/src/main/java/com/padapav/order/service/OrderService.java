package com.padapav.order.service;

import com.padapav.order.model.*;
import com.padapav.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.customerId());
        long total = 0L;
        for (OrderLineRequest line : request.items()) {
            total = Math.addExact(total, Math.multiplyExact(line.unitPrice(), line.quantity().longValue()));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setMenuItemId(line.menuItemId());
            item.setMenuItemName(line.menuItemName());
            item.setQuantity(line.quantity());
            item.setUnitPrice(line.unitPrice());
            order.getItems().add(item);
        }
        order.setTotalPrice(total);
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findAllByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    @Transactional
    public Order updateStatus(Long id, OrderStatus next) {
        Order order = getOrder(id);
        OrderStatus current = order.getStatus();
        boolean allowed = switch (current) {
            case CREATED -> next == OrderStatus.PAYMENT_PENDING || next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;
            case PAYMENT_PENDING -> next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;
            case CONFIRMED -> next == OrderStatus.PREPARING || next == OrderStatus.CANCELLED;
            case PREPARING -> next == OrderStatus.READY_FOR_PICKUP;
            case READY_FOR_PICKUP -> next == OrderStatus.OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY -> next == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
        if (!allowed) throw new IllegalStateException("Invalid order status transition: " + current + " -> " + next);
        order.setStatus(next);
        return orderRepository.save(order);
    }
}
