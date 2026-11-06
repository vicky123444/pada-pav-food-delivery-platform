# Pada Pav Food Delivery Platform

A learning-oriented food ordering and delivery platform for **Pada Pav, Pav Bhaji, Sandwiches, Momos and Mumbai Chaap**.

The project is intended to demonstrate Java 21, Spring Boot microservices, React, Docker, Kubernetes, OpenSearch and AWS through a restaurant-ordering workflow.

> **Status:** The uploaded project has been reviewed as a starter scaffold. It is not yet a complete or production-ready food-delivery product. See [Implementation Status](docs/implementation-status.md) for capability-by-capability findings. The source bundle is available separately as `pada-pav-food-delivery-platform-github-ready.zip`; follow [Local setup and publishing](docs/github-publish.md) to add the extracted source tree to this repository.

## Business workflow

1. Customer browses the menu and selects food items.
2. Customer submits an order.
3. Payment is initiated or Cash on Delivery is selected.
4. Restaurant accepts the order.
5. Kitchen prepares the food and marks it ready.
6. Delivery is assigned, picked up and completed.
7. Customer and restaurant staff receive order updates.

## Technology stack

| Area | Technology | Current state |
|---|---|---|
| Backend | Java 21, Spring Boot | Nine service modules in starter form |
| API routing | Spring Cloud Gateway | Route starter; security requires hardening |
| Frontend | React, TypeScript, Vite | Customer/admin prototypes using sample data |
| Database | PostgreSQL / Amazon RDS | Persistence starter |
| Cache | Redis / ElastiCache | Local container only; application integration pending |
| Search | OpenSearch | Infrastructure starter; search code is a stub |
| Messaging | SQS / SNS / EventBridge | Terraform starter; durable service integration pending |
| Containers | Docker, Docker Compose | Dockerfiles and Compose configuration |
| Orchestration | Kubernetes, Helm, Amazon EKS | Starter manifests/chart; not all services configured |
| Cloud | AWS | Terraform development foundation |
| CI/CD | GitHub Actions | Workflow is prepared; must run successfully after source is pushed |
| Monitoring | Actuator, CloudWatch, OpenTelemetry | Partial; production dashboards/traces remain |

## Services

| Service | Responsibility |
|---|---|
| `api-gateway` | Routes external API requests |
| `catalog-service` | Menu items, categories, prices and availability |
| `customer-service` | Customer profiles and delivery addresses |
| `order-service` | Order creation and order-state transitions |
| `payment-service` | Payment attempts and payment status (currently simulated) |
| `kitchen-service` | Restaurant acceptance and preparation workflow |
| `delivery-service` | Delivery assignment and status |
| `search-service` | Menu search (OpenSearch integration pending) |
| `notification-service` | Notification API (currently an in-memory demo) |

## Repository layout

```text
apps/                  React customer and restaurant-admin apps
services/              Spring Boot microservices
contracts/              OpenAPI and event schema contracts
docs/                   Architecture, API, data model, runbooks and status
infra/docker-compose/   Local supporting services
infra/kubernetes/       Kubernetes starter manifests
infra/helm/              Helm starter chart
infra/terraform/         AWS development infrastructure
scripts/                 Windows PowerShell start/demo scripts
.github/workflows/       CI workflow
```

## Local prerequisites

- JDK 21
- Maven 3.9+
- Docker Desktop or Docker Engine with Docker Compose v2
- Node.js 22+ and npm for the React applications
- Terraform 1.5+ only when reviewing/provisioning AWS infrastructure

## Local startup

After adding the extracted source tree to this repository:

```powershell
./scripts/start-local.ps1
```

Or manually:

```bash
mvn -B -DskipTests package
docker compose up --build -d
docker compose ps
```

The API Gateway is intended to listen on `http://localhost:8080`. Start each React app in a separate terminal:

```bash
cd apps/customer-web
npm install
npm run dev
```

```bash
cd apps/restaurant-admin
npm install
npm run dev
```

The current frontend applications are prototypes with sample data and are not yet fully connected to backend APIs.

## Engineering topics demonstrated

- Microservice boundaries and REST API design
- PostgreSQL persistence and database migrations
- Order lifecycle and state validation
- Event-driven architecture, SQS/SNS and the transactional outbox pattern (planned integration)
- Docker images and local Compose environments
- Kubernetes Deployments, Services, probes, resources and Helm
- OpenSearch indexing, text search and hybrid/vector search (planned integration)
- AWS VPC, EKS, RDS, S3, OpenSearch and messaging
- Terraform, GitHub Actions, observability and production hardening

## Security and production warning

Do not expose this starter publicly or use it for live restaurant transactions until authentication/authorization, payment-provider integration, backend price validation, durable messaging, secrets management, network restrictions, automated tests, monitoring and backup/restore are completed.

The current gateway security is permissive, payment processing is simulated, notification storage is in-memory, search is a sample implementation, and AWS resources have not been validated with a full Terraform plan/apply. Demo credentials in local Compose must never be reused in shared or production environments.

## Documentation

- [Architecture](docs/architecture.md)
- [Implementation status and requirement audit](docs/implementation-status.md)
- [Production hardening roadmap](docs/production-hardening.md)
- [Local setup and GitHub publishing](docs/github-publish.md)
- [WordPress article draft](docs/blog-wordpress-draft.md)

## License

No license has been selected. Until a license is added, the default copyright applies; do not assume the code is open source or reusable without permission.
