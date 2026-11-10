package com.padapav.notification.event;
import com.padapav.notification.model.NotificationMessage;
import com.padapav.notification.service.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Component
public class OrderConfirmedEventListener {
    private final NotificationService notificationService;
    public OrderConfirmedEventListener(NotificationService notificationService) { this.notificationService = notificationService; }
    @EventListener
    public void onOrderConfirmed(OrderConfirmedEvent event) {
        NotificationMessage message = new NotificationMessage();
        message.setType("ORDER_CONFIRMED");
        message.setChannel("EMAIL");
        message.setRecipient("customer-" + event.customerId());
        message.setMessage("Order " + event.orderId() + " has been confirmed and is being prepared.");
        notificationService.sendNotification(message);
    }
}
