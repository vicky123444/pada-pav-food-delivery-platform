CREATE TABLE IF NOT EXISTS delivery_assignments (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    rider_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
