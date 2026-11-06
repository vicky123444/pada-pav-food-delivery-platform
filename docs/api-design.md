# API Design

## Core endpoints

### Catalog service
- `GET /api/v1/menu`
- `GET /api/v1/menu/{id}`
- `POST /api/v1/admin/menu-items`

### Customer service
- `GET /api/v1/customers/me`
- `POST /api/v1/customers/addresses`

### Order service
- `POST /api/v1/orders`
- `GET /api/v1/orders/{id}`
- `GET /api/v1/customers/me/orders`

### Payment service
- `POST /api/v1/payments`
- `GET /api/v1/payments/{id}`

### Kitchen service
- `GET /api/v1/kitchen/orders`
- `PATCH /api/v1/kitchen/orders/{id}/accept`
- `PATCH /api/v1/kitchen/orders/{id}/ready`

### Delivery service
- `GET /api/v1/delivery/assignments`
- `POST /api/v1/delivery/assignments`
- `PATCH /api/v1/delivery/assignments/{id}/status`

### Search service
- `GET /api/v1/search/menu?q=cheese%20sandwich`
- `GET /api/v1/search/menu?category=momos`
- `GET /api/v1/search/menu?category=chaap&sort=price_asc`

### Notification service
- `GET /api/v1/notifications`
- `POST /api/v1/notifications`

## API conventions

- JSON request and response bodies.
- Consistent HTTP status codes.
- Validation with Jakarta Bean Validation.
- Event-driven notifications for asynchronous updates (durable cross-service integration remains to be implemented).
