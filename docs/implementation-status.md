# Implementation status and requirement audit

This document describes the current repository source as a learning/portfolio starter. A configuration file or container definition alone does not prove that its integration is operational.

## CI baseline and recent fixes

The earlier GitHub Actions run failed before Java compilation because the notification Maven POM was malformed and the declared Payment and Search modules did not exist. The restaurant-admin TypeScript configuration also contained invalid JSON syntax. Those reported issues have been corrected, missing service starter modules added, and the Order service implementation and migration scaffold completed.

The corrected commits trigger GitHub Actions automatically. Check the repository Actions tab for the latest result. Docker startup, Terraform plan/apply and end-to-end checkout have not been performed as part of this source update.

## Requirement traceability

| Area | Current state | Remaining work |
|---|---|---|
| Java 21 / Spring Boot | Nine Maven service modules exist | Confirm Maven verify passes on CI and add broader automated tests |
| Customer React app | Vite/React prototype with sample data | API integration, login, cart, address, checkout and tracking |
| Restaurant admin | Static dashboard prototype | Live orders, staff authentication, status actions and menu management |
| API Gateway | Spring Cloud Gateway routes exist | Current authorization is permissive; add JWT/OIDC, RBAC, rate limits and CORS |
| Catalog | Menu REST endpoints and JPA model | Backend validation, migrations, administration authorization and tests |
| Customer | Profile/address API and persistence starter | Identity integration, ownership authorization and tests |
| Order | Creation, item persistence, legal state transitions and Flyway schema | Validate item prices and availability with Catalog; add locking/idempotency, events and tests |
| Payment | Simulated in-memory API with idempotent-per-order demo behavior | Real provider sandbox, verified webhooks, persistence, refunds and reconciliation |
| Kitchen | Basic state persistence/endpoints | Durable order-event integration, staff authorization and concurrency controls |
| Delivery | Demo assignment/status flow | Real dispatch/driver assignment and location tracking |
| Search | In-memory demo menu search | OpenSearch client, indexing, query/filter, IAM and catalog synchronization |
| Notifications | In-memory API and local event-listener examples | Durable queue consumers, persistent storage and email/SMS providers |
| PostgreSQL / RDS | Local PostgreSQL and Terraform configuration | Separate schemas/users, production migrations, backups and secret rotation |
| Redis / ElastiCache | Local Redis container only | Add application caching and managed AWS integration |
| OpenSearch | Local/AWS infrastructure starter | Connect Search service and apply authentication/network controls |
| SQS / SNS / EventBridge | Terraform starter resources | Producers/consumers, retries, DLQs, idempotency and service wiring |
| Docker | Service Dockerfiles and root Compose file | Run compose build/start on Docker host and verify health |
| Kubernetes / Helm | Starter manifests/chart | Manifests for all services, probes, limits, ingress, HPA, secrets and network policies |
| EKS / Terraform | Development infrastructure foundation | Run fmt, validate, plan and apply review in a controlled AWS account |
| Security | Local demo settings only | Cognito/OIDC, ownership/RBAC, least privilege and secret management |
| Observability | Actuator endpoints are partial | Central logs, metrics, tracing, dashboards and alarms |
| CI/CD | Maven and React build workflow configured | Confirm current green run; add scans, image publishing and controlled deployment |

## Security and data correctness

1. Gateway authorization is currently permissive. Do not expose this application publicly.
2. Local credentials are for development only and must not be reused in shared or production environments.
3. Order creation currently accepts item names and unit prices from the caller. A production implementation must fetch authoritative prices and availability from Catalog and calculate totals server-side.
4. Payment is a simulation only; it does not contact a payment provider, process real money or verify webhooks.
5. Search and notification data are in memory and will be lost when the process restarts.
6. Use separate service database identities/schemas, private networking and Secrets Manager before deployment.

## Overall assessment

The repository now has the nine service modules, both frontend prototypes, contracts, infrastructure scaffolding and developer documentation. It is a useful engineering starter but not an end-to-end or production-ready food-delivery platform. Prioritize a tested customer-to-kitchen order flow, server-side pricing, authentication/authorization and durable payment/event integration.
