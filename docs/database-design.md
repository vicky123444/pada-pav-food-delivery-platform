# Database Design

## Core entities

### Catalog service
- MenuItem
- Category
- Variation
- AddOn

### Customer service
- Customer
- Address

### Order service
- Order
- OrderItem
- OrderStatusHistory

### Payment service
- Payment
- PaymentAttempt
- Refund

### Kitchen service
- KitchenOrder
- KitchenAssignment

### Delivery service
- DeliveryAssignment
- DeliveryUpdate

## Notes

- Use PostgreSQL as the transaction database.
- Redis can be used for temporary cart and session data once application integration is added.
- OpenSearch stores search-optimized menu documents and is not the payment/order source of truth.
- Keep event publication idempotent and durable with an outbox table.
