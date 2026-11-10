# Pada Pav Food Delivery Platform

A learning-oriented food ordering and delivery platform for Vada Pav, Pav Bhaji, Sandwiches, Momos and Mumbai Chaap.

The repository contains a Java 21 / Spring Boot microservices starter, React/TypeScript customer and restaurant-admin prototypes, API contracts, Docker Compose, Kubernetes/Helm starter manifests, Terraform infrastructure and engineering documentation.

> **Status:** This is a portfolio and learning starter, not a production-ready food-delivery product. Payment is simulated and stored in memory; search uses demo menu data rather than OpenSearch; notifications are stored in memory; frontend apps still use sample data. Read [Implementation Status](docs/implementation-status.md) before using or extending the project.

## Services

| Service | Port | Responsibility |
|---|---:|---|
| API Gateway | 8080 | Routes platform APIs |
| Catalog | 8081 | Menu items and pricing |
| Customer | 8082 | Customer profiles and addresses |
| Order | 8083 | Order creation and status transitions |
| Payment | 8084 | Simulated payment attempts |
| Kitchen | 8085 | Restaurant acceptance and food preparation |
| Delivery | 8086 | Delivery assignment and status |
| Search | 8087 | Demo menu search |
| Notification | 8088 | In-memory notification API |

## Technology

- Java 21, Spring Boot 3, Spring Cloud Gateway, Maven
- React 18, TypeScript and Vite
- PostgreSQL, Redis and OpenSearch containers
- Docker and Docker Compose
- Kubernetes, Helm and Terraform starter files
- GitHub Actions for Maven and frontend builds

## Repository layout

```text
apps/                 React customer and restaurant-admin prototypes
services/             Nine Spring Boot service modules
contracts/            OpenAPI and event schema examples
docs/                 Architecture, data model, runbooks and status audit
infra/                Docker Compose, Kubernetes, Helm and Terraform
scripts/              Local startup/demo scripts
.github/workflows/    Build and test workflow
```

## Prerequisites

- JDK 21
- Maven 3.9+
- Docker Desktop or Docker Engine with Compose v2
- Node.js 22+ and npm

## Build

From the repository root:

```bash
mvn -B verify
```

Build each frontend:

```bash
cd apps/customer-web
npm install
npm run build

cd ../restaurant-admin
npm install
npm run build
```

## Run locally

First package all Java services, then start containers:

```bash
mvn -B -DskipTests package
docker compose up --build -d
docker compose ps
```

API Gateway is intended to listen on `http://localhost:8080`. Run the React apps separately with `npm run dev` from each app folder.

## Example APIs

- `GET /api/v1/menu` — catalog menu
- `POST /api/v1/orders` — create an order
- `GET /api/v1/orders/{id}` — retrieve an order
- `PATCH /api/v1/orders/{id}/status?status=CONFIRMED` — transition order status
- `POST /api/v1/payments` — create a simulated payment
- `GET /api/v1/search?q=vada` — demo menu search

## Important limitations

The gateway's current authorization policy is permissive. Payment processing is not connected to a real provider and must never be used to collect money. Prices submitted to the order API are not yet validated against the Catalog service. Search is not connected to OpenSearch, notification events are local/in-process examples, and the customer/admin frontends are not fully integrated with backend APIs.

Do not expose this project publicly or process live orders until authentication/authorization, server-side price validation, real payment-provider integration, durable messaging, secrets management, automated integration tests, monitoring and backup/restore are implemented.

## Documentation

- [Architecture](docs/architecture.md)
- [Implementation status and requirement audit](docs/implementation-status.md)
- [Production hardening roadmap](docs/production-hardening.md)
- [Local setup and repository workflow](docs/github-publish.md)
- [Database design](docs/database-design.md)
- [Runbooks](docs/runbooks.md)

## License

No license has been selected. Until a license is added, the default copyright applies.
