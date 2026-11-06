# Order Lifecycle and State Model

The platform follows a restaurant order workflow from creation to delivery.

## States

`CREATED`, `PAYMENT_PENDING`, `CONFIRMED`, `ACCEPTED`, `PREPARING`, `READY`, `OUT_FOR_DELIVERY`, `DELIVERED`, `CANCELLED`, `PAYMENT_FAILED`.

## Intended valid transitions

- CREATED -> PAYMENT_PENDING
- PAYMENT_PENDING -> CONFIRMED
- PAYMENT_PENDING -> PAYMENT_FAILED
- CONFIRMED -> ACCEPTED
- ACCEPTED -> PREPARING
- PREPARING -> READY
- READY -> OUT_FOR_DELIVERY
- OUT_FOR_DELIVERY -> DELIVERED
- Any non-terminal state -> CANCELLED

Validate that these transitions match the actual service implementation before relying on them as an enforced workflow.

## Business notes

- Payment should be validated before kitchen acceptance where applicable.
- Order state transitions must be enforced server-side.
- Event publication should be handled after successful persistence using an outbox pattern.
