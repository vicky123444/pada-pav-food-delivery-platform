# Production hardening checklist

This is a learning starter. Complete and test the following before exposing it to real customers, handling real payments or deploying it as a production restaurant platform.

## Identity and API security

- Configure Cognito/OIDC or an equivalent identity provider.
- Validate JWT issuer, audience, signature, expiry and scopes.
- Enforce customer ownership and staff/admin roles at service level, not only in the frontend.
- Replace permissive gateway rules; define route authorization, CORS and rate limits.
- Use TLS at every public boundary and restrict service-to-service access.
- Apply least-privilege IAM roles to each workload.

## Orders and payments

- Recalculate current price, quantity limits, availability, discounts, tax and delivery charges on the server.
- Define valid order state transitions and reject stale/concurrent updates.
- Use idempotency keys for order creation and payment operations.
- Verify signed payment webhooks and reconcile payment state with the provider.
- Implement refunds, cancellation rules and Cash on Delivery as explicit workflows.
- Do not store card numbers or CVV data.

## Messaging and consistency

- Implement a transactional outbox for events published after database commits.
- Make consumers idempotent.
- Configure retry/backoff and dead-letter queues.
- Add event schema versions, correlation IDs and trace context.
- Define recovery procedures for events that cannot be processed.

## Data and infrastructure

- Keep RDS private and allow connections only from approved application security groups.
- Store credentials in Secrets Manager; rotate credentials.
- Enable encryption at rest and in transit.
- Configure RDS automated backups, retention and restore tests.
- Keep OpenSearch in private subnets with IAM/fine-grained access control.
- Treat OpenSearch as a derived index, not the transactional source of truth.
- Restrict S3 bucket access; enable encryption, lifecycle and appropriate versioning.
- Configure EKS workload identity, RBAC, network policies, pod security settings and image scanning.
- Use Terraform plan review and environment-specific state backends. Never commit Terraform state or production tfvars.

## Reliability and operations

- Add readiness/liveness/startup probes and graceful shutdown.
- Set CPU/memory requests and limits, HPA and disruption budgets.
- Add structured JSON logs, metrics, distributed tracing and service dashboards.
- Define alerts and SLOs for order acceptance, payment success and order completion.
- Test dependency outages, queue backlog, pod restarts and database failover.
- Load-test the end-to-end order journey.
- Document backup/restore, rollback, incident and credential-rotation runbooks.

## Release gate

Do not call the system production-ready until the complete customer-to-kitchen-to-delivery flow passes integration and end-to-end tests, payment state is verified with a provider sandbox, authorization tests pass, infrastructure has been reviewed and a restore/rollback exercise has been completed.
