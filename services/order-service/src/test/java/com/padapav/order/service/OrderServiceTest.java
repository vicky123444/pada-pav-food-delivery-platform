package com.padapav.order.service;

import com.padapav.order.model.*;
import com.padapav.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock OrderRepository repository;
    @InjectMocks OrderService service;

    @Test
    void createsOrderAndCalculatesTotalInMinorUnits() {
        OrderRequest request = new OrderRequest(7L,
            java.util.List.of(new OrderLineRequest(3L, "Vada Pav", 2, 2500L)));
        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = service.createOrder(request);

        assertEquals(7L, order.getCustomerId());
        assertEquals(5000L, order.getTotalPrice());
        assertEquals(OrderStatus.CREATED, order.getStatus());
        assertEquals(1, order.getItems().size());
        assertEquals(2, order.getItems().get(0).getQuantity());
    }

    @Test
    void rejectsInvalidStatusTransition() {
        Order order = new Order();
        order.setId(11L);
        order.setStatus(OrderStatus.CREATED);
        when(repository.findById(11L)).thenReturn(Optional.of(order));

        assertThrows(IllegalStateException.class, () -> service.updateStatus(11L, OrderStatus.DELIVERED));
        verify(repository, never()).save(any());
    }
}
