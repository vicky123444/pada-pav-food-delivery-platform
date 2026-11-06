package com.padapav.notification.event;

import com.padapav.notification.model.NotificationMessage;
import com.padapav.notification.service.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentPendingEventListener {

    private final NotificationService notificationService;

    public OrderPaymentPendingEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void onOrderPaymentPending(com.padapav.order.event.OrderPaymentPendingEvent event) {
        NotificationMessage message = new NotificationMessage();
        message.setType("ORDER_PAYMENT_PENDING");
        message.setChannel("SMS");
        message.setRecipient("customer-" + event.customerId());
        message.setMessage("Order " + event.orderId() + " is awaiting payment confirmation.");
        notificationService.sendNotification(message);
    }
}
