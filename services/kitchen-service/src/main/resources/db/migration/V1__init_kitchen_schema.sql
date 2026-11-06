CREATE TABLE IF NOT EXISTS kitchen_orders (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    notes VARCHAR(255)
);
