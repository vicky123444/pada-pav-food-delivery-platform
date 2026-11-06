# Local Development Runbook

## Prerequisites
- JDK 21 (the POM currently targets Java 21).
- Maven 3.9+ on `PATH`.
- Docker Desktop / Docker Engine with Compose v2.
- Node.js 22+ and npm for the frontend apps.
- PowerShell 7 or Windows PowerShell for helper scripts.

## Start the complete local stack (Windows PowerShell)

From the repository root:

```powershell
./scripts/start-local.ps1
```

The script packages the Java modules (tests are skipped only for this demo startup) and starts the root `docker-compose.yml`. It uses Docker Compose service DNS for service-to-service URLs.

## Start manually

```bash
mvn -B -DskipTests package
docker compose up --build -d
docker compose ps
```

Stop the stack:

```bash
docker compose down
```

Remove local data too (destructive):

```bash
docker compose down -v
```

## Frontend apps

In separate terminals:

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

The current frontends are visual prototypes with static sample data; they are not yet connected to backend APIs.

## Service ports
- API Gateway: 8080
- Catalog: 8081
- Customer: 8082
- Order: 8083
- Payment: 8084
- Kitchen: 8085
- Delivery: 8086
- Search: 8087
- Notification: 8088
- PostgreSQL: 5432
- Redis: 6379 (not yet used by service code)
- OpenSearch: 9200 (security disabled only in this local Compose setup; search-service still uses a stub)

## Useful checks
- Gateway health: `http://localhost:8080/actuator/health`
- Menu: `http://localhost:8080/api/v1/menu`
- Search demo: `http://localhost:8080/api/v1/search/menu?q=sandwich`

## Demo order flow

```powershell
./scripts/full-platform-demo.ps1
```

The script exercises the current demo endpoints. It is not a substitute for integration tests. If it fails, preserve the container logs (`docker compose logs --tail=200`) and use the implementation-status document to identify known integration gaps.

## Security warning

Compose uses demo-only credentials, and OpenSearch security is disabled for local development. Never expose these containers to an untrusted network or reuse the credentials in a real environment.
